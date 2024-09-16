package com.project.desafio_jpc.list.presentation.list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.desafio_jpc.commom.ConnectionException
import com.project.desafio_jpc.list.domain.usecase.CharacterUseCase
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.CharacterEvent
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.ListCharacterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


internal class CharacterViewModel(
    private val useCase: CharacterUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {


    private val _uiState = MutableStateFlow(ListCharacterState())
    val uiState: StateFlow<ListCharacterState> = _uiState.asStateFlow()

    var uiEvent by mutableStateOf<CharacterEvent>(CharacterEvent.Nothing)

    init {
        getAllCharacter(incrementPage = false)
    }


    fun getAllCharacter(incrementPage: Boolean = false) = viewModelScope.launch(dispatcher) {
        var hasCache = false
        useCase.invoke(_uiState.value.listCharacter.size)
            .onStart {
                _uiState.update {
                    it.copy(
                        isLoading = !incrementPage,
                        isLoadingPagination = incrementPage
                    )
                }
            }
            .catch {
                if (!hasCache) {
                    onError(it)
                }
            }
            .collect {
                hasCache = true
                val currentList = if (incrementPage) {
                    _uiState.value.listCharacter.apply {
                        addAll(it)
                    }
                } else {
                    it.toMutableList()
                }
                _uiState.update { state ->
                    state.copy(
                        listCharacter = currentList.distinct().toMutableList(),
                        isLoading = false,
                        isLoadingPagination = false
                    )
                }

            }
    }

    private fun onError(exception: Throwable) {
        if (uiState.value.listCharacter.isNotEmpty()) {
            _uiState.update { it.copy(isErrorWithCache = true) }
            return
        }
        when (exception) {
            is ConnectionException -> {
                _uiState.update { it.copy(isGenericError = false, isError = true, isLoading = false, isLoadingPagination = false) }
            }

            else -> {
                _uiState.update { it.copy(isGenericError = true, isError = true, isLoading = false, isLoadingPagination = false) }
            }
        }
    }

    fun onNavigateToDetail(id: String) {
        uiEvent = CharacterEvent.NavigateToDetail(id)
    }

    fun clearEvent() {
        uiEvent = CharacterEvent.Nothing
    }

    fun onNavigateToProfile() {
        uiEvent = CharacterEvent.NavigateToProfile
    }
}
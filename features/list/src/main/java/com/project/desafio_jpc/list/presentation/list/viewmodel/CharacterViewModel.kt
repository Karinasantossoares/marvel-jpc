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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class CharacterViewModel(
    private val useCase: CharacterUseCase,
) : ViewModel() {


    private val _uiState = MutableStateFlow(ListCharacterState())
    val uiState: StateFlow<ListCharacterState> = _uiState.asStateFlow()

    var uiEvent by mutableStateOf<CharacterEvent>(CharacterEvent.Nothing)

    private var requestPending = false

    init {
        getAllCharacter(incrementPage = false)
    }


    fun getAllCharacter(incrementPage: Boolean = false) = viewModelScope.launch {
        val currentPage = _uiState.value.getNextPage(incrementPage)
        useCase.invoke(currentPage.toString())
            .onStart {
                _uiState.update {
                    it.copy(
                        isLoading = !incrementPage,
                        isLoadingPagination = incrementPage
                    )
                }
            }
            .onCompletion { _uiState.update { it.copy(isLoading = false, isLoadingPagination = false) } }
            .catch { onError(it) }
            .collect {
                val currentList = if (incrementPage) {
                    _uiState.value.listCharacter.apply { addAll(it) }
                } else {
                    it.toMutableList()
                }
                _uiState.update { state ->
                    state.copy(
                        listCharacter = currentList
                    )
                }

            }
    }

    private fun onError(exception: Throwable) {
        when (exception) {
            is ConnectionException -> {
                _uiState.update { it.copy(isGenericError = false, isError = true) }
            }

            else -> {
                _uiState.update { it.copy(isGenericError = true, isError = true) }
            }
        }
    }

    fun onNavigateToDetail(id: String) {
        uiEvent = CharacterEvent.NavigateToDetail(id)
    }

    fun onNavigateBack() {
        uiEvent = CharacterEvent.NavigateToBack
    }

    fun onChangeTab(position: Int) {
        _uiState.update {
            it.copy(
                tabSelected = position
            )
        }
    }

    fun clearEvent() {
        uiEvent = CharacterEvent.Nothing
    }

    fun onNavigateToProfile() {
        uiEvent = CharacterEvent.NavigateToProfile
    }
}
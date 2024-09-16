package com.project.desafio_jpc.list.presentation.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.desafio_jpc.commom.ConnectionException
import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.usecase.DetailUseCase
import com.project.desafio_jpc.list.presentation.detail.viewmodel.model.CharacterDetailEvent
import com.project.desafio_jpc.list.presentation.detail.viewmodel.model.CharacterDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class DetailViewModel(
    private val useCase: DetailUseCase,
    private val id: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailState())
    val uiState: StateFlow<CharacterDetailState> = _uiState.asStateFlow()

    var uiEvent by mutableStateOf<CharacterDetailEvent>(CharacterDetailEvent.Nothing)

    init {
        getDetailCharacter()
    }


    private fun getDetailCharacter() = viewModelScope.launch {
        useCase.invoke(id)
            .onStart { _uiState.update { it.copy(isLoading = true) } }
            .onCompletion { _uiState.update { it.copy(isLoading = false) } }
            .catch { onError(it) }
            .collect { onSuccess(it) }
    }

    private fun onSuccess(detailModel: CharacterDetailModel) {
        _uiState.update {
            it.setLoading(false)
            it.setSuccess(detailModel)
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

    fun clearEvent() {
        uiEvent = CharacterDetailEvent.Nothing
    }

    fun onBackClick(){
        uiEvent = CharacterDetailEvent.OnBack
    }
}
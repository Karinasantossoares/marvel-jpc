package com.project.desafio_jpc.list.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.desafio_jpc.list.presentation.detail.screen.CharacterDetailScreen
import com.project.desafio_jpc.list.presentation.detail.viewmodel.DetailViewModel
import com.project.desafio_jpc.list.presentation.detail.viewmodel.model.CharacterDetailEvent
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun CharacterDetailRoute(
    id: String,
    viewModel: DetailViewModel = koinViewModel { parametersOf(id) },
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (viewModel.uiEvent) {
        is CharacterDetailEvent.OnBack -> {
            onBack()
        }

        CharacterDetailEvent.Nothing -> {}
    }
    viewModel.clearEvent()

    CharacterDetailScreen(state = uiState)
}
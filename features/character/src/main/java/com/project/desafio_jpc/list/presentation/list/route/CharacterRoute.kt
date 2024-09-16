package com.project.desafio_jpc.list.presentation.list.route

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.desafio_jpc.list.presentation.list.screen.CharacterScreen
import com.project.desafio_jpc.list.presentation.list.viewmodel.CharacterViewModel
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.CharacterEvent
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CharacterRoute(
    viewModel: CharacterViewModel = koinViewModel(),
    goToDetail: (String) -> Unit,
    goToProfile: () -> Unit
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val event: CharacterEvent = viewModel.uiEvent) {
        is CharacterEvent.NavigateToDetail -> {
            goToDetail(event.id)
        }

        CharacterEvent.NavigateToProfile -> {
            goToProfile()
        }

        CharacterEvent.Nothing -> {}

    }
    viewModel.clearEvent()

    val lazyListState = rememberLazyListState()

    val endOfListReached by remember {
        derivedStateOf {
            val lastVisibleItemIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex != null && lastVisibleItemIndex >= uiState.listCharacter.size - 1
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached) {
            viewModel.getAllCharacter(true)
        }
    }


    CharacterScreen(
        state = uiState,
        lazyListState = lazyListState,

        goToDetail = { id ->
            viewModel.onNavigateToDetail(id)
        },

        goToProfile = {
            viewModel.onNavigateToProfile()
        }
    )
}
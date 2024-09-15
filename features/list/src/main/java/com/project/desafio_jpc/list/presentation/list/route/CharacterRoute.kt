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
    onBackClick: () -> Unit,
    goToProfile: () -> Unit
) {


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val lazyListState = rememberLazyListState()

    val endOfListReached by remember {
        derivedStateOf {
            val lastVisibleItemIndex = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleItemIndex == uiState.listCharacter.size - 1
        }
    }
    LaunchedEffect(endOfListReached) {
        if (endOfListReached) {
            viewModel.getAllCharacter(true)
        }
    }

    when (val event: CharacterEvent = viewModel.uiEvent) {
        is CharacterEvent.NavigateToDetail -> {
            goToDetail(event.id)
        }

        CharacterEvent.NavigateToBack -> {
            onBackClick()
        }

        CharacterEvent.NavigateToProfile -> {
            goToProfile()
        }

        CharacterEvent.Nothing -> {}

    }
    viewModel.clearEvent()

    CharacterScreen(
        state = uiState,
        lazyListState = lazyListState,
        onBack = {
            viewModel.onNavigateBack()
        },
        goToDetail = { id ->
            viewModel.onNavigateToDetail(id)
        },
        onChangeTab = { position ->
            viewModel.onChangeTab(position)
        },
        goToProfile = {
            viewModel.onNavigateToProfile()
        }
    )
}
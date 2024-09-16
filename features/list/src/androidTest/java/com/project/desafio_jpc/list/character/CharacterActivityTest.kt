package com.project.desafio_jpc.list.character

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.test.junit4.createComposeRule
import com.project.desafio_jpc.list.presentation.list.screen.CharacterScreen
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CharacterActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = CharacterActionRobot(composeTestRule)

    @Test
    fun testShowListCharactersWithSuccess() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen()
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyTitleAppBar()
            .verifyDescriptionText()
            .verifyListCharacter()
    }

    @Test
    fun testShowCenterLoading() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen(loadingCenter = true)
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyCenterLoading()
    }

    @Test
    fun testShowCenterBottom() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen(loadingBottom = true)
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyBottomLoading()
    }

    @Test
    fun testShowError() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen(isError = true)
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyTemplateError()
    }

    @Test
    fun testMessageGenericError() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen(isError = true, isGenericError = true)
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyOnGenericMessageError()
    }

    @Test
    fun testMessageConnectionError() {
        robot.startScreen {
            val uiState = getStateListCharacterScreen(isError = true, isGenericError = false)
            val lazyListState = LazyListState()
            CharacterScreen(
                state = uiState,
                lazyListState = lazyListState,
                goToDetail = {},
                goToProfile = {}
            )
        }.verifyOnConnectionMessageError()
    }
}
package com.project.desafio_jpc.list.character

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex

class CharacterActionRobot(
    private val composeTestRule: ComposeContentTestRule
) {


    fun startScreen(composable: @Composable () -> Unit) = apply {
        composeTestRule.setContent {
            composable()
        }
    }

    fun verifyTitleAppBar() = apply {
        composeTestRule.onNodeWithText("Personagens").assertExists()
    }

    fun verifyDescriptionText() = apply {
        composeTestRule.onNodeWithText("Listagem de personanges da Marvel").assertExists()
    }

    fun verifyCenterLoading() = apply {
        composeTestRule
            .onNodeWithTag("loading_center")
            .assertExists()
            .assertIsDisplayed()
    }

    fun verifyBottomLoading() = apply {
        composeTestRule
            .onNodeWithTag("lazy_column")
            .performScrollToIndex(3)
        composeTestRule
            .onNodeWithTag("loading_bottom")
            .assertExists()
            .assertIsDisplayed()
    }

    fun verifyTemplateError() = apply {
        composeTestRule
            .onNodeWithTag("template_error")
            .assertExists()
            .assertIsDisplayed()
    }

    fun verifyOnGenericMessageError() = apply {
        composeTestRule
            .onNodeWithTag("text_error")
            .assertTextContains("Opa, algo deu errado. Tente novamento mais tarde.")
            .assertIsDisplayed()
    }

    fun verifyOnConnectionMessageError() = apply {
        composeTestRule
            .onNodeWithTag("text_error")
            .assertTextContains("Opa, parece que você está sem internet.")
            .assertIsDisplayed()
    }


    fun verifyListCharacter() = apply {
        composeTestRule.onAllNodes(hasText("Homem de ferro 1")).assertCountEquals(1)
        composeTestRule.onAllNodes(hasText("Homem de ferro 2")).assertCountEquals(1)
        composeTestRule.onAllNodes(hasText("Homem de ferro 3")).assertCountEquals(1)
        composeTestRule.onAllNodes(hasText("Homem de ferro 4")).assertCountEquals(1)
    }

}
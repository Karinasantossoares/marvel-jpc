package com.project.desafio_jpc.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.project.desafio_jpc.commom.ConnectionException
import com.project.desafio_jpc.list.domain.usecase.CharacterUseCase
import com.project.desafio_jpc.list.presentation.list.viewmodel.CharacterViewModel
import com.project.desafio_jpc.list.presentation.list.viewmodel.model.ListCharacterState
import getCharacterModelStub
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = MainDispatcherRule()

    private val useCase: CharacterUseCase = mockk(relaxed = true)
    private lateinit var viewModel: CharacterViewModel

    private fun createViewModel() {
        viewModel = CharacterViewModel(useCase, dispatcher = coroutinesTestRule.testDispatcher)
    }


    @Test
    fun `when call refresh with success return list of characters`() = runTest {
        // Given
        val expectedList = getCharacterModelStub()
        every { useCase.invoke(offset = 0) } returns flow { emit(expectedList) }

        // When
        createViewModel()

        viewModel.uiState.test {
            assertEquals(
                ListCharacterState(
                    listCharacter = expectedList.toMutableList(),
                    isLoading = false,
                ), awaitItem()
            )
        }
    }

    @Test
    fun `when call refresh with error return list of character`() = runTest {
        // Given
        val errorExpected = Exception()
        every { useCase.invoke(0) } returns flow { throw errorExpected }


        // When
        createViewModel()

        viewModel.uiState.test {
            assertEquals(
                ListCharacterState(
                    isError = true, isGenericError = true
                ), awaitItem()
            )
        }
    }

    @Test
    fun `when call refresh with error connection return list of character`() = runTest {
        // Given
        val errorExpected = ConnectionException()
        every { useCase.invoke(0) } returns flow { throw errorExpected }


        // When
        createViewModel()

        viewModel.uiState.test {
            assertEquals(
                ListCharacterState(
                    isError = true, isGenericError = false
                ), awaitItem()
            )
        }
    }

    @Test
    fun `when call refresh with error connection return list of character with cache`() = runTest {
        // Given
        val expectedCache = getCharacterModelStub()
        val errorExpected = ConnectionException()
        every { useCase.invoke(0) } returns flow {
            emit(getCharacterModelStub())
            throw errorExpected
        }


        // When
        createViewModel()

        viewModel.uiState.test {
            assertEquals(
                ListCharacterState(
                    listCharacter = expectedCache.toMutableList()
                ), awaitItem()
            )
        }
    }
}
package com.project.desafio_jpc.list.repository

import app.cash.turbine.test
import com.project.desafio_jpc.commom.ConnectionException
import com.project.desafio_jpc.commom.HttpException
import com.project.desafio_jpc.list.data.datasource.local.CharacterLocalDataSource
import com.project.desafio_jpc.list.data.datasource.remote.CharacterRemoteDataSource
import com.project.desafio_jpc.list.data.repository.CharacterRepositoryImpl
import getCharacterModelStub
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterRepositoryIntegrationTest {

    private val remoteDataSource: CharacterRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: CharacterLocalDataSource = mockk(relaxed = true)
    private val repository = CharacterRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `getCharacter should return character when useCase return success`() = runBlocking {
        val expected = getCharacterModelStub()
        val currentOffset = 20

        coEvery { remoteDataSource.getAllCharacters(currentOffset) } returns flowOf(expected)
        coEvery { localDataSource.getAllCharacters(currentOffset) } returns expected
        coEvery {
            localDataSource.deleteByPage(currentOffset)
        } returns Unit
        coEvery {
            localDataSource.insertAllCharacters(getCharacterModelStub(), currentOffset)
        } returns Unit

        val result = repository.getAllCharacter(currentOffset)


        result.test {
            assertEquals(expected, awaitItem())
            assertEquals(expected, awaitItem())
            awaitComplete()
        }
    }


    @Test
    fun `getCharacterHome Should return an error When service returns DefaultError`() =
        runBlocking {
            // Given
            val currentPage = 20
            coEvery { remoteDataSource.getAllCharacters(currentPage) } throws HttpException
            coEvery { localDataSource.getAllCharacters(currentPage.toInt()) } returns emptyList()

            // When
            val result = repository.getAllCharacter(currentPage)

            result.test {
                assertEquals(HttpException, awaitError())
            }
        }

    @Test
    fun `getCharacterHome Should return an error When service returns IOException`() = runBlocking {
        // Given
        val currentPage = 20
        coEvery { remoteDataSource.getAllCharacters(currentPage) } throws ConnectionException()
        coEvery { localDataSource.getAllCharacters(currentPage) } returns emptyList()

        // When
        val result = repository.getAllCharacter(currentPage)

        result.test {
            assertEquals(ConnectionException(), awaitError())
        }
    }
}

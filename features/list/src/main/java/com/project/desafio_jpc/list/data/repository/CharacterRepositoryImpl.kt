package com.project.desafio_jpc.list.data.repository

import com.project.desafio_jpc.list.data.datasource.local.CharacterLocalDataSource
import com.project.desafio_jpc.list.data.datasource.remote.CharacterRemoteDataSource
import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

internal class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) : CharacterRepository {


    override fun getAllCharacter(offset: Int): Flow<List<CharacterModel>> = flow {
        val localResult = localDataSource.getAllCharacters(offset)
        if (localResult.isNotEmpty()) {
            emit(localResult)
        }

        emitAll(
            remoteDataSource.getAllCharacters(offset).onEach { result ->
                localDataSource.deleteByPage(page = offset)
                localDataSource.insertAllCharacters(result, offset)
            }
        )

    }

    override fun getDetailCharacter(id: String): Flow<CharacterDetailModel> {
        return remoteDataSource.getDetailCharacters(id)
    }
}
package com.project.desafio_jpc.list.data.repository

import com.project.desafio_jpc.list.data.datasource.remote.CharacterRemoteDataSource
import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

internal class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {

    override fun getAllCharacter(incrementPage: String?): Flow<List<CharacterModel>> {
        return remoteDataSource.getAllCharacters(incrementPage)
    }

    override fun getDetailCharacter(id: String): Flow<CharacterDetailModel> {
        return remoteDataSource.getDetailCharacters(id)
    }
}
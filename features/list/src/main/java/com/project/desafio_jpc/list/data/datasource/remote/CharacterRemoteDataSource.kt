package com.project.desafio_jpc.list.data.datasource.remote

import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

internal interface CharacterRemoteDataSource {
     fun getAllCharacters(incrementPage: String?): Flow<List<CharacterModel>>

     fun getDetailCharacters(id: String): Flow<CharacterDetailModel>
}
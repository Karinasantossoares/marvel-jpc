package com.project.desafio_jpc.list.domain.repository

import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

internal interface CharacterRepository {
    fun getAllCharacter(incrementPage: String?): Flow<List<CharacterModel>>
    fun getDetailCharacter(id: String): Flow<CharacterDetailModel>
}
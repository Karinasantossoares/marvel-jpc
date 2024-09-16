package com.project.desafio_jpc.list.data.datasource.local

import com.project.desafio_jpc.list.domain.model.CharacterModel

internal interface CharacterLocalDataSource {
    suspend fun getAllCharacters(page :Int): List<CharacterModel>
    suspend fun insertAllCharacters(characters: List<CharacterModel>, incrementPage: Int)
    suspend fun deleteByPage(page: Int)
}
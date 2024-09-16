package com.project.desafio_jpc.list.data.datasource.local

import com.project.desafio_jpc.list.data.mapper.toEntity
import com.project.desafio_jpc.list.data.mapper.toModel
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.persistence.dao.CharacterDao

class CharacterLocalDataSourceImpl(
    private val characterDao: CharacterDao
) : CharacterLocalDataSource {
    override suspend fun getAllCharacters(page: Int): List<CharacterModel> {
        return characterDao.getAllCharacters(page).map { characterEntity ->
            characterEntity.toModel()
        }
    }

    override suspend fun insertAllCharacters(characters: List<CharacterModel>, incrementPage: Int) {
        characterDao.insertCharacters(characters.map { characterModel ->
            characterModel.toEntity(incrementPage)
        })
    }

    override suspend fun deleteByPage(page: Int) {
        characterDao.deleteByPage(page)
    }
}
package com.project.desafio_jpc.list.domain.usecase

import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

internal class CharacterUseCase(private val repository: CharacterRepository) {
    fun invoke(incrementPage: String?): Flow<List<CharacterModel>> {
        return repository.getAllCharacter(incrementPage)
    }
}
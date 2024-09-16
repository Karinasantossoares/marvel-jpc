package com.project.desafio_jpc.list.domain.usecase

import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

internal class DetailUseCase(private val repository: CharacterRepository) {
    fun invoke(id: String): Flow<CharacterDetailModel> = repository.getDetailCharacter(id)
}
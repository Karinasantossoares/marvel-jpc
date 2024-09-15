package com.project.desafio_jpc.list.data.mapper

import com.project.desafio_jpc.list.data.model.CharacterResultsResponse
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.model.ThumbnailModel


fun List<CharacterResultsResponse>.toModel(): List<CharacterModel> {
    return map {
        CharacterModel(
            id = it.id,
            name = it.name ?: "",
            description = it.description ?: "",
            thumbnail = ThumbnailModel(
                it.thumbnail?.path ?: "",
                it.thumbnail?.extension ?: ""
            )
        )
    }
}
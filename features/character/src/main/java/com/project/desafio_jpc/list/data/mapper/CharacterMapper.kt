package com.project.desafio_jpc.list.data.mapper

import com.project.desafio_jpc.list.data.model.CharacterResultsResponse
import com.project.desafio_jpc.list.domain.model.CharacterModel
import com.project.desafio_jpc.list.domain.model.ThumbnailModel
import com.project.desafio_jpc.persistence.entity.CharacterEntity
import com.project.desafio_jpc.persistence.entity.ThumbnailEntity


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

fun CharacterEntity.toModel(): CharacterModel {
    return CharacterModel(
        id = id.toString(),
        name = name,
        description = description,
        thumbnail = thumbnail.toModel()
    )
}

fun ThumbnailEntity.toModel(): ThumbnailModel {
    return ThumbnailModel(
        path = path,
        extension = extension
    )
}

fun CharacterModel.toEntity(page: Int): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail.toEntity(),
        page = page
    )
}

fun ThumbnailModel.toEntity(): ThumbnailEntity {
    return ThumbnailEntity(
        path = path,
        extension = extension
    )
}
package com.project.desafio_jpc.list.data.mapper

import com.project.desafio_jpc.list.data.model.DetailResponse
import com.project.desafio_jpc.list.domain.model.CharacterDetailModel
import com.project.desafio_jpc.list.domain.model.CommicsModel
import com.project.desafio_jpc.list.domain.model.ItemsModel
import com.project.desafio_jpc.list.domain.model.ThumbnailModel

fun DetailResponse.toModel(): CharacterDetailModel {
    return data.results.orEmpty().first().let {
        CharacterDetailModel(
            name = it.name,
            description = it.description,
            thumbnail = ThumbnailModel(
                extension = it.thumbnail?.extension.orEmpty(),
                path = it.thumbnail?.path.orEmpty()
            ),
            comics = CommicsModel(
                itemsModel = it.comics?.itemsResponse?.map { items ->
                    ItemsModel(
                        resourceUri = items.resourceUri,
                        name = items.name
                    )
                }
            )
        )
    }
}
package com.project.desafio_jpc.list.domain.model

data class CharacterDetailModel(
    val name: String? = null,
    val description: String? = null,
    val thumbnail: ThumbnailModel? = null,
    val comics: CommicsModel? = null
)

data class CommicsModel(
    val itemsModel: List<ItemsModel>? = null
)

data class ItemsModel(
    val resourceUri: String? = null,
    val name: String? = null
)
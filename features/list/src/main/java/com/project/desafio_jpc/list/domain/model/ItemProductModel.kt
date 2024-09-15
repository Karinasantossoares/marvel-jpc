package com.project.desafio_jpc.list.domain.model

data class CharacterModel(
    val id: String,
    val name: String,
    val description:String,
    val thumbnail: ThumbnailModel = ThumbnailModel()
)

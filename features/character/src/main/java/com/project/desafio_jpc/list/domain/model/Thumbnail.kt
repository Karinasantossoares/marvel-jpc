package com.project.desafio_jpc.list.domain.model

data class ThumbnailModel(
    val path: String = "",
    val extension: String = ""
){
    fun getUrlImage(): String {
        return "${path}.${extension}".replace("http", "https")
    }
}
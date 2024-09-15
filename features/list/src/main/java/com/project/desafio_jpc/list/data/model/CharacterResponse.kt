package com.project.desafio_jpc.list.data.model

import com.google.gson.annotations.SerializedName

data class CharacterResultsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse? = null
)
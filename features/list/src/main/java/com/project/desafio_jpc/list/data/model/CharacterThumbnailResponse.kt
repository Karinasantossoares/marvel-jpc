package com.project.desafio_jpc.list.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
)
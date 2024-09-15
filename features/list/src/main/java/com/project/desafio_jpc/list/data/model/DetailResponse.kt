package com.project.desafio_jpc.list.data.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("data") val data: DataResponse
)

data class DataResponse(
    @SerializedName("results") val results: List<ResultsResponse>? = null
)

data class ResultsResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("thumbnail") val thumbnail: ThumbnailResponse? = null,
    @SerializedName("comics") val comics: CommicsResponse? = null
)

data class CommicsResponse(
    @SerializedName("items") val itemsResponse: List<ItemsResponse>
)

data class ItemsResponse(
    @SerializedName("resourceURI") val resourceUri: String? = null,
    @SerializedName("name") val name: String? = null
)
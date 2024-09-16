package com.project.desafio_jpc.list.data.service

import com.project.desafio_jpc.list.data.model.CharacterResultsResponse
import com.project.desafio_jpc.list.data.model.DetailResponse
import com.project.desafio_jpc.list.data.model.ResultMarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CharacterService {

    @GET("v1/public/characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): ResultMarvelResponse<List<CharacterResultsResponse>?>

    @GET("/v1/public/characters/{id}")
    suspend fun getDetailCharacter(@Path(value = "id") uuid: String) : DetailResponse
}
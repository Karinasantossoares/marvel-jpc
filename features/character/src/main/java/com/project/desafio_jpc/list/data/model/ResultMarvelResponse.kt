package com.project.desafio_jpc.list.data.model

data class ResultMarvelResponse<T>(
    val data: ResultMarvelListResponse<T>
)
data class ResultMarvelListResponse<T>(
    val results: T
)
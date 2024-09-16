package com.project.desafio_jpc.list.domain.model

data class ResultMarvel<T>(
    val data: ResultMarvelList<T>
)
data class ResultMarvelList<T>(
    val results: T
)
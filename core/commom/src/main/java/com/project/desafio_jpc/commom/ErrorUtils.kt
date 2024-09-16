package com.project.desafio_jpc.commom

data object HttpException : Exception()
data class ConnectionException(
    val hasCache:Boolean = false
) : Exception()
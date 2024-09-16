package com.project.desafio_jpc.persistence.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey val id: String = "",
    val name: String,
    val description: String,
    @Embedded
    val thumbnail: ThumbnailEntity,
    val page: Int = 0
)

data class ThumbnailEntity(
    val path: String,
    val extension: String
)
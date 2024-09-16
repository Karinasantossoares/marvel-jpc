package com.project.desafio_jpc.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.desafio_jpc.persistence.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character where page = :page")
    suspend fun getAllCharacters(page: Int): List<CharacterEntity>

    @Query("DELETE  FROM character where page = :page")
    suspend fun deleteByPage(page: Int)

}

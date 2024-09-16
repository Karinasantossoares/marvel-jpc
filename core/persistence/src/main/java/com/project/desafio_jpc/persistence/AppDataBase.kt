package com.project.desafio_jpc.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.desafio_jpc.persistence.dao.CharacterDao
import com.project.desafio_jpc.persistence.entity.CharacterEntity

@Database(exportSchema = false, entities = [CharacterEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        fun instance(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, "AppDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}

package com.project.desafio_jpc.persistence.di

import com.project.desafio_jpc.persistence.AppDataBase
import org.koin.dsl.module


object PersistenceModule {
    fun persistenceModule() = module {
        single { AppDataBase.instance(get()) }
        single { get<AppDataBase>().characterDao() }
    }
}

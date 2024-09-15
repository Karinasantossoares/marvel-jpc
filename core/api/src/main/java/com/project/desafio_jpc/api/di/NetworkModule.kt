package com.project.desafio_jpc.api.di

import com.project.desafio_jpc.api.retrofit.RetrofitConfig
import org.koin.dsl.module

object NetworkModule {
    fun networkModules(baseUrl: String) = module {
        single { RetrofitConfig().createRetrofit(baseUrl) }
    }
}
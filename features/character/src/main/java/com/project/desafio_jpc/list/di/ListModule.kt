package com.project.desafio_jpc.list.di

import com.project.desafio_jpc.list.data.datasource.local.CharacterLocalDataSource
import com.project.desafio_jpc.list.data.datasource.local.CharacterLocalDataSourceImpl
import com.project.desafio_jpc.list.data.datasource.remote.CharacterRemoteDataSource
import com.project.desafio_jpc.list.data.datasource.remote.CharacterRemoteRemoteDataSourceImpl
import com.project.desafio_jpc.list.data.repository.CharacterRepositoryImpl
import com.project.desafio_jpc.list.data.service.CharacterService
import com.project.desafio_jpc.list.domain.repository.CharacterRepository
import com.project.desafio_jpc.list.domain.usecase.CharacterUseCase
import com.project.desafio_jpc.list.domain.usecase.DetailUseCase
import com.project.desafio_jpc.list.presentation.detail.viewmodel.DetailViewModel
import com.project.desafio_jpc.list.presentation.list.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object ListModule {
    fun listModule() = module {
        factory { get<Retrofit>().create(CharacterService::class.java) }
        factory<CharacterRemoteDataSource> { CharacterRemoteRemoteDataSourceImpl(get()) }
        factory<CharacterLocalDataSource> { CharacterLocalDataSourceImpl(get()) }
        factory<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
        factory { CharacterUseCase(repository = get()) }
        factory { CharacterViewModel(useCase = get()) }

        factory { DetailUseCase(repository = get()) }
        viewModel { (id: String) ->
            DetailViewModel(
                useCase = get(),
                id = id,
            )
        }
    }
}
package com.project.desafio_jpc.detail.presentation.di

import com.project.desafio_jpc.detail.presentation.navigation.ProfileNavigationImpl
import com.project.desafio_jpc.navigation.ProfileNavigation
import org.koin.dsl.module

object ProfileModule {
    fun profileModules() = module {
        factory<ProfileNavigation> { ProfileNavigationImpl() }
    }
}
package com.project.desafio_jpc

import android.app.Application
import com.project.desafio_jpc.api.di.NetworkModule
import com.project.desafio_jpc.detail.presentation.di.ProfileModule
import com.project.desafio_jpc.list.di.ListModule
import com.project.desafio_jpc.persistence.di.PersistenceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                NetworkModule.networkModules(BuildConfig.BASE_URL),
                ListModule.listModule(),
                ProfileModule.profileModules(),
                PersistenceModule.persistenceModule()
            )
        }
    }
}
package com.pranaysoni.randomusers.base

import android.app.Application
import com.pranaysoni.randomusers.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RandomUsersApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RandomUsersApp)
            modules(allModules)
        }
    }
}
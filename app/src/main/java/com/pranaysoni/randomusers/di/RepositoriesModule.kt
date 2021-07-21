package com.pranaysoni.randomusers.di


import com.pranaysoni.randomusers.di.repositories.HomeScreenRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { HomeScreenRepository(get()) }
}
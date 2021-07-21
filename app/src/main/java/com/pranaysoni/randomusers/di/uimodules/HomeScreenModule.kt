package com.pranaysoni.randomusers.di.uimodules

import com.pranaysoni.randomusers.viewmodels.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeScreenModule = module {
    viewModel { HomeScreenViewModel(get(),get()) }
}
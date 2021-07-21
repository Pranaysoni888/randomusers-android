package com.pranaysoni.randomusers.di

import com.pranaysoni.randomusers.di.uimodules.homeScreenModule
import org.koin.core.module.Module

val uiModules: Array<Module> = arrayOf(
    homeScreenModule
)
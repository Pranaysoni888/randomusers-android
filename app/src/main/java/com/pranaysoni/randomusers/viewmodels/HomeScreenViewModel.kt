package com.pranaysoni.randomusers.viewmodels

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.pranaysoni.randomusers.base.BaseViewModel
import com.pranaysoni.randomusers.di.repositories.HomeScreenRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application, private val dashboardRepository: HomeScreenRepository): BaseViewModel(application) {

    val cityListStatus: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun getUsersList() {
        viewModelScope.launch {
            cityListStatus.addSource(
                dashboardRepository.getUsersList()
            ){
                cityListStatus.value = it
            }

        }
    }
}
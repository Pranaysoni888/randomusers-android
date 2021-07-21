package com.pranaysoni.randomusers.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pranaysoni.randomusers.di.ReliableViewModel


abstract class BaseViewModel(application: Application) :  AndroidViewModel(application),
    ReliableViewModel {
    private val TAG = BaseViewModel::class.java.simpleName

}

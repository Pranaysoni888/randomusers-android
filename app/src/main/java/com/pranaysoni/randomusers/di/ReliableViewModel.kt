package com.pranaysoni.randomusers.di

import android.os.Bundle
import androidx.lifecycle.LifecycleObserver

interface ReliableViewModel : LifecycleObserver {
    fun writeTo(bundle: Bundle) {
        //
    }

    fun readFrom(bundle: Bundle?) {
        //
    }
}
package com.example.apptareas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import it.xabaras.android.recyclerview.swipedecorator.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class TareasApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
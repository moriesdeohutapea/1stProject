package com.project.firstproject

import android.app.Application
import com.project.core.di.coreModule
import com.project.firstproject.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class untuk inisialisasi global dependency Koin.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule, coreModule)
        }
    }
}


package com.fueled.technicalchallenge

import android.app.Application
import com.fueled.technicalchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChallengeApplication)
            modules(appModule)
        }
    }
}
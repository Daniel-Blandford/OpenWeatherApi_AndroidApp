package com.roger.lloyd_pack

import android.app.Application
import com.roger.lloyd_pack.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
            // other Koin configurations
        }
    }
}
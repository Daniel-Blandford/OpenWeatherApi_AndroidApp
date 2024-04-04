package com.roger.lloyd_pack

import android.app.Application
import com.roger.lloyd_pack.di.appModule
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class AppModuleTest : KoinTest {
    private val mockApplication = mockk<Application>(relaxed = true)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun checkAllModules() {
        koinApplication {
            androidContext(mockApplication)
            modules(appModule)
        }.checkModules()
    }
}
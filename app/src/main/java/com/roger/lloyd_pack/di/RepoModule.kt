package com.roger.lloyd_pack.di

import com.roger.lloyd_pack.data.PreviousSearchDataStore
import com.roger.lloyd_pack.repository.WeatherRepository
import com.roger.lloyd_pack.repository.WeatherRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoModule = module {
    singleOf(::WeatherRepositoryImpl) { bind<WeatherRepository>() }
    single { PreviousSearchDataStore(androidApplication()) }
}
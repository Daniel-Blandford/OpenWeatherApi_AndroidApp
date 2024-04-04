package com.roger.lloyd_pack.di

import com.roger.lloyd_pack.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::WeatherViewModel)
}
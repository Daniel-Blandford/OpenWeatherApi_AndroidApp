package com.roger.lloyd_pack.di

import org.koin.dsl.module

val appModule = module {
    includes(
        repoModule,
        viewModelModule,
    )
}
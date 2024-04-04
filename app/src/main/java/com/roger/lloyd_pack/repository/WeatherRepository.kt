package com.roger.lloyd_pack.repository

import com.roger.lloyd_pack.viewmodel.UIState
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherDetails(locationQuery: String?): Flow<UIState>
}
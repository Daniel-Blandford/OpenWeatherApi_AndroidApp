package com.roger.lloyd_pack.viewmodel

import com.roger.lloyd_pack.data.OpenWeatherResponseModel

sealed class UIState {
    data class Success(val data: OpenWeatherResponseModel): UIState()
    data class Failure(val error: String): UIState()
    data class Loading(val isLoading: Boolean = true): UIState()
    object Empty: UIState()
}
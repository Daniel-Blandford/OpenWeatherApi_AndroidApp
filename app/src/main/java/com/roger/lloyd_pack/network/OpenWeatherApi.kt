package com.roger.lloyd_pack.network

import com.roger.lloyd_pack.data.OpenWeatherResponseModel
import com.roger.lloyd_pack.network.NetworkConstants.API_ENDPOINT
import com.roger.lloyd_pack.network.NetworkConstants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET(API_ENDPOINT)
    suspend fun getWeatherDetails(
        @Query("q") location: String? = null,    // for regions: "London"
        @Query("APPID") appId: String = API_KEY,
    ): Response<OpenWeatherResponseModel>
}
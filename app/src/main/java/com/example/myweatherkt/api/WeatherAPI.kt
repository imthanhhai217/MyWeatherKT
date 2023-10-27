package com.example.myweatherkt.api

import com.example.myweatherkt.models.CurrentWeatherResponse
import com.example.myweatherkt.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET(Constants.CURRENT_WEATHER_URL)
    suspend fun getCurrentWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String? = "en"
    ): Response<CurrentWeatherResponse>
}
package com.anit.goodweather.repository.rest.request


import com.anit.goodweather.repository.rest.entites.WeatherRequestRestModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeather {
    @GET("data/2.5/weather")
    fun loadWeather(
        @Query("q") city: String,
        @Query("appid") keyApi: String,
        @Query("units") units: String
    ): Call<WeatherRequestRestModel>
}

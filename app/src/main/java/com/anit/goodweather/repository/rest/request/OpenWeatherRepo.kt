package com.anit.goodweather.repository.rest.request

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenWeatherRepo private constructor() {
    val api: IOpenWeather

    init {
        api = createAdapter()
    }

    private fun createAdapter(): IOpenWeather {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return adapter.create(IOpenWeather::class.java)
    }

    companion object {
        const val baseUrl = "http://api.openweathermap.org/"
        private var singleton: OpenWeatherRepo  = OpenWeatherRepo()
        @Synchronized
        fun getInstance(): OpenWeatherRepo {
            return singleton
        }
    }
}

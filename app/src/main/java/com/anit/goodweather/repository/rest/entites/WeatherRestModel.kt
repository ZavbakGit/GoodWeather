package com.anit.goodweather.repository.rest.entites

import com.google.gson.annotations.SerializedName

data class WeatherRestModel (
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null
)

package com.anit.goodweather.repository.rest.entites

import com.google.gson.annotations.SerializedName

data class WindRestModel (
    @SerializedName("speed")
    var speed: Float = 0.toFloat(),
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
)

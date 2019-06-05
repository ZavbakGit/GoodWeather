package com.anit.goodweather.repository.rest.entites

import com.google.gson.annotations.SerializedName

data class CoordRestModel (
    @SerializedName("lon")
    var lon: Float = 0.toFloat(),
    @SerializedName("lat")
    var lat: Float = 0.toFloat()
)

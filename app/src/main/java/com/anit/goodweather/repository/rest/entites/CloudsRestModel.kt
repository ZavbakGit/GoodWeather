package com.anit.goodweather.repository.rest.entites

import com.google.gson.annotations.SerializedName

data class CloudsRestModel (
    @SerializedName("all")
    var all: Int = 0
)

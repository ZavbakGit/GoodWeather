package com.anit.goodweather.repository.database.entites

import java.util.*

data class CityItem(
    val city:String,
    val temperature:Float,
    val icon:String,
    val date: Date
)
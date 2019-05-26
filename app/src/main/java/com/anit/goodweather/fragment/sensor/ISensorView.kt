package com.anit.goodweather.fragment.sensor

import android.content.Context

interface ISensorView{
    fun showTemperature(nameSensor:String,data:String)
    fun showHumidity(nameSensor:String,data:String)
    fun showLight(nameSensor:String,data:String)
    fun getContext(): Context
}
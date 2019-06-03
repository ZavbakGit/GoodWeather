package com.anit.goodweather.fragment.weather

import android.content.Context

interface IWeatherView{
    fun showInfo(city:String,temperature:String)
    fun getContext():Context
}
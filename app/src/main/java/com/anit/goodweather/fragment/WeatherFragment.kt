package com.anit.goodweather.fragment

import com.anit.goodweather.R


class WeatherFragment: BaseFragment(){

    companion object {
        val TAG = "WeatherFragment"
        fun newInstance() = WeatherFragment()
    }

    override fun getLayout() = R.layout.weather_fragment

}
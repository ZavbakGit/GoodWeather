package com.anit.goodweather.fragment

import android.arch.lifecycle.LifecycleObserver
import com.anit.goodweather.R


class WeatherFragment: BaseFragment(), LifecycleObserver {

    companion object {
        val TAG = "WeatherFragment"
        fun newInstance() = WeatherFragment()
    }

    override fun getLayout() = R.layout.weather_fragment

}
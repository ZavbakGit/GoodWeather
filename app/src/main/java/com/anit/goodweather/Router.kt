package com.anit.goodweather

import com.anit.goodweather.fragment.AboutFragment
import com.anit.goodweather.fragment.FeedbackFragment
import com.anit.goodweather.fragment.WeatherFragment

class Router(private val activity: MainActivity, private val container:Int):IRouter{
    override fun startAbout() {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(container, AboutFragment.newInstance(), AboutFragment.TAG)
            .commit()
    }

    override fun startWeather() {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(container, WeatherFragment.newInstance(), WeatherFragment.TAG)
            .commit()
    }

    override fun startFeedback() {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(container, FeedbackFragment.newInstance(), FeedbackFragment.TAG)
            .commit()
    }

}
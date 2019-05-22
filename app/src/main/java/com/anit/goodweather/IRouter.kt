package com.anit.goodweather

import com.anit.goodweather.fragment.AboutFragment
import com.anit.goodweather.fragment.FeedbackFragment
import com.anit.goodweather.fragment.WeatherFragment

class IRouter(val activity: MainActivity,var container:Int):Router{
    override fun startAbout() {
        activity.getSupportFragmentManager()
            .beginTransaction()
            .replace(container, AboutFragment.newInstance(), AboutFragment.TAG)
            .commit()
    }

    override fun startWeather() {
        activity.getSupportFragmentManager()
            .beginTransaction()
            .replace(container, WeatherFragment.newInstance(), WeatherFragment.TAG)
            .commit()
    }

    override fun startFeedback() {
        activity.getSupportFragmentManager()
            .beginTransaction()
            .replace(container, FeedbackFragment.newInstance(), FeedbackFragment.TAG)
            .commit()
    }





}
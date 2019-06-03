package com.anit.goodweather.fragment.weather

import android.os.Handler
import android.preference.PreferenceManager
import com.anit.goodweather.R
import com.anit.goodweather.repository.WeatherDataLoader

class WeatherPresenter(val view:IWeatherView){

    companion object{
        const val KEY_CITY = "pref_city"
    }

    private val handler = Handler()

    var city:String? = null
    set(value) {
        field = value
        saveToPreference()
        refreshData()
    }

    fun refreshData(){
        city?.let{
            object : Thread() {
                override fun run() {
                    val jsonObject = WeatherDataLoader.getJSONData(city!!)
                    if (jsonObject == null) {

                    } else {

                        val main = jsonObject.getJSONObject("main")
                        val currentTextText =
                            String.format("%.2f", main.getDouble("temp")) + "\u2103"

                        handler.post{
                            view.showInfo(city!!,currentTextText)
                        }
                    }
                }
            }.start()
        }

    }

    private fun saveToPreference() {
        val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(
            view.getContext().applicationContext
        )
        val editor = defaultPrefs.edit()

        editor.putString(KEY_CITY, city)
        editor.apply()
    }

    fun readPreference() {
        val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(
            view.getContext().applicationContext
        )
        val text = defaultPrefs.getString(KEY_CITY, view.getContext().getString(R.string.default_city))
        city = text
    }

}
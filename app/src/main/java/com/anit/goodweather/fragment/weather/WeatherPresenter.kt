package com.anit.goodweather.fragment.weather


import android.preference.PreferenceManager
import com.anit.goodweather.R
import com.anit.goodweather.repository.rest.entites.WeatherRequestRestModel
import com.anit.goodweather.repository.rest.request.OpenWeatherRepo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenter(val view: IWeatherView) {

    companion object {
        const val KEY_CITY = "pref_city"
    }

    var city: String? = null
        set(value) {
            field = value
            saveToPreference()
            refreshData()
        }

    fun refreshData() {

        OpenWeatherRepo.getInstance().api.loadWeather(
            city ?: "",
            "762ee61f52313fbd10a4eb54ae4d4de2",
            "metric"
        )
            .enqueue(object : Callback<WeatherRequestRestModel> {
                override fun onResponse(
                    call: Call<WeatherRequestRestModel>,
                    response: Response<WeatherRequestRestModel>
                ) {
                    if (response.body() != null && response.isSuccessful) {
                        val model = response.body() as WeatherRequestRestModel

                        val info = (model.main?.temp ?: 0f).toString()
                        view.showInfo(city!!, info + "\u2103")

                        val icon = model.weather?.get(0)?.icon
                        if (icon != null) {
                            view.loadImage("${OpenWeatherRepo.baseUrl}img/w/$icon.png")
                        } else {
                            view.loadImage(null)
                        }
                    } else {
                        view.showInfo(city!!, "error")
                        view.loadImage(null)
                    }
                }

                override fun onFailure(call: Call<WeatherRequestRestModel>, t: Throwable) {
                    view.showInfo(city!!, "error")
                    view.loadImage(null)
                }
            })

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
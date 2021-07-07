package com.anit.goodweather.repository

import org.json.JSONObject

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class WeatherDataLoader {
    companion object {
        private const val OPEN_WEATHER_API_KEY = "f3f2763fe63803beef4851d6365c83bc"
        private const val OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric"
        private const val KEY = "x-api-key"
        private const val RESPONSE = "cod"
        private const val ALL_GOOD = 200

        fun getJSONData(city: String): JSONObject? {

            try {
                val url = URL(String.format(OPEN_WEATHER_API_URL, city))
                val connection = url.openConnection() as HttpURLConnection
                connection.addRequestProperty(KEY, OPEN_WEATHER_API_KEY)

                val reader = BufferedReader(InputStreamReader(connection.inputStream) as Reader)


                var strData = reader
                    .lineSequence()
                    .fold(StringBuilder(1024)) { buff, line -> buff.append(line) }
                    .toString()

                reader.close()

                val jsonObject = JSONObject(strData)

                return if (jsonObject.getInt(RESPONSE) != ALL_GOOD) {
                    null
                } else {
                    jsonObject
                }
            } catch (e: Exception) {
                return null
            }
        }
    }



}

package com.anit.goodweather.fragment.sensor

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import com.anit.goodweather.R


class SensorPresenter(
    private val lifecycle: Lifecycle,
    private val sensorView: ISensorView
) : LifecycleObserver {

    private val nameSensorLight =
        sensorView.getContext().getString(R.string.light) ?: ""

    private val nameSensorHumidity =
        sensorView.getContext().getString(R.string.humidity) ?: ""

    private val nameSensorTemperature =
        sensorView.getContext().getString(R.string.temperature) ?: ""

    private val messNotFound =
        sensorView.getContext().getString(R.string.not_found) ?: ""

    init {
        initSensor()
    }

    private fun initSensor() {

        val sensorManager = sensorView.getContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            SensorChangedListenerHandler(
                sensorManager = sensorManager,
                type = Sensor.TYPE_LIGHT,
                lifecycle = lifecycle,
                funSensorChanged = {
                    sensorView.showLight(
                        nameSensorLight, "${it.values[0]}"
                    )
                }
            )
        } else {
            sensorView.showLight(
                nameSensorLight, messNotFound
            )
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            SensorChangedListenerHandler(
                sensorManager = sensorManager,
                type = Sensor.TYPE_RELATIVE_HUMIDITY,
                lifecycle = lifecycle,
                funSensorChanged = {
                    sensorView.showHumidity(
                        nameSensorHumidity, "${it.values[0]}"
                    )
                }
            )
        } else {
            sensorView.showHumidity(
                nameSensorHumidity, messNotFound
            )
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            SensorChangedListenerHandler(
                sensorManager = sensorManager,
                type = Sensor.TYPE_AMBIENT_TEMPERATURE,
                lifecycle = lifecycle,
                funSensorChanged = {
                    sensorView.showTemperature(
                        nameSensorTemperature, "${it.values[0]}"
                    )
                }
            )
        } else {
            sensorView.showTemperature(
                nameSensorTemperature, messNotFound
            )
        }
    }


}




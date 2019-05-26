package com.anit.goodweather.fragment.sensor

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent


class SensorPresenter(
    private var sensorManager: SensorManager,
    var lifecycle: Lifecycle,
    private val sensorView: ISensorView
) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private val listenerTemperature = SensorChangedListenerHandler {
        sensorView.showTemperature("Температура: ${it.values[0]}")
    }

    private val listenerHumidity = SensorChangedListenerHandler {
        sensorView.showHumidity("Влажность: ${it.values[0]}")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onStart(source: LifecycleOwner, event: Lifecycle.Event) {

        when (event) {
            Lifecycle.Event.ON_START -> {
                sensorManager.registerListener(
                    listenerTemperature,
                    sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                    SensorManager.SENSOR_DELAY_NORMAL
                )

                sensorManager.registerListener(
                    listenerHumidity,
                    sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),
                    SensorManager.SENSOR_DELAY_NORMAL
                )

            }
            Lifecycle.Event.ON_STOP -> {
                sensorManager.unregisterListener(listenerTemperature)
                sensorManager.unregisterListener(listenerHumidity)
            }
            else -> {}
        }
    }

    class SensorChangedListenerHandler(val funSensorChanged: (event: SensorEvent) -> Unit)
        : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

        override fun onSensorChanged(event: SensorEvent) {
            funSensorChanged(event)
        }

    }

}
package com.anit.goodweather.fragment.sensor

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager


class SensorChangedListenerHandler(
    private val sensorManager: SensorManager,
    private val type: Int,
    lifecycle: Lifecycle,
    private val funSensorChanged: (event: SensorEvent) -> Unit

) : SensorEventListener, LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun stateUpdated(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                sensorManager.registerListener(
                    this,
                    sensorManager.getDefaultSensor(type),
                    SensorManager.SENSOR_DELAY_NORMAL
                )

            }
            Lifecycle.Event.ON_STOP -> {
                sensorManager.unregisterListener(this)
            }
            else -> {
            }

        }

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        funSensorChanged(event)
    }

}
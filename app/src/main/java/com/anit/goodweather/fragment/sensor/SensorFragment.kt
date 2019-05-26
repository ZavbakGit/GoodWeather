package com.anit.goodweather.fragment.sensor


import android.os.Bundle
import com.anit.goodweather.R
import com.anit.goodweather.fragment.BaseFragment
import kotlinx.android.synthetic.main.sensor_fragment.*


class SensorFragment: BaseFragment(),ISensorView {

    private lateinit var presenter: SensorPresenter

    companion object {
        const val TAG = "SensorFragment"
        fun newInstance() = SensorFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.presenter = SensorPresenter(
            lifecycle = lifecycle,
            sensorView = this
        )

    }

    override fun getLayout() = R.layout.sensor_fragment

    override fun showTemperature(nameSensor:String,data: String) {
        (sen_view_light as CustomSensorView).setSensorName(nameSensor)
        (sen_view_light as CustomSensorView).setSensorData(data)
    }

    override fun showHumidity(nameSensor:String,data: String) {
        (sen_view_humidity as CustomSensorView).setSensorName(nameSensor)
        (sen_view_humidity as CustomSensorView).setSensorData(data)
    }

    override fun showLight(nameSensor:String,data: String) {
        (sen_view_temperature as CustomSensorView).setSensorName(nameSensor)
        (sen_view_temperature as CustomSensorView).setSensorData(data)
    }

    override fun getContext() = activity!!

}
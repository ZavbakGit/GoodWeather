package com.anit.goodweather.fragment.sensor


import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import com.anit.goodweather.R
import com.anit.goodweather.fragment.BaseFragment


class SensorFragment: BaseFragment(),ISensorView {

    private lateinit var presenter: SensorPresenter

    companion object {
        const val TAG = "SensorFragment"
        fun newInstance() = SensorFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SensorPresenter(
            sensorManager = (activity!!.getSystemService(Context.SENSOR_SERVICE) as SensorManager?)!!,
            lifecycle = lifecycle,
            sensorView = this
        )
    }

    override fun getLayout() = R.layout.sensor_fragment

    override fun showTemperature(str: String) {
        Toast.makeText(activity,str,Toast.LENGTH_SHORT).show()
    }

    override fun showHumidity(str: String) {
        Toast.makeText(activity,str,Toast.LENGTH_SHORT).show()
    }

}
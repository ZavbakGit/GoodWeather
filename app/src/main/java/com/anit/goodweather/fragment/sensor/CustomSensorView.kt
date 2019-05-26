package com.anit.goodweather.fragment.sensor

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import com.anit.goodweather.R


class CustomSensorView : RelativeLayout {

    private var sensorName:TextView? = null
    private var sensorData:TextView? = null



    constructor(context: Context) : super(context) {
        initViews(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initViews(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initViews(context)
    }

    private fun initViews(context: Context) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.sensor_view, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initViews()
    }

    private fun initViews(){
        sensorName = this.findViewById(R.id.sensor_name)
        sensorData = this.findViewById(R.id.sensor_data)
    }

    fun setSensorName(name:String){
        sensorName?.apply {
            text = name
        }
    }

    fun setSensorData(name:String){
        sensorData?.apply {
            text = name
        }
    }

}

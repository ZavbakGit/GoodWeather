package com.anit.goodweather.fragment.weather


import android.content.Context
import com.anit.goodweather.R
import com.anit.goodweather.fragment.BaseFragment
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : BaseFragment(), IWeatherView {

    companion object {
        const val TAG = "WeatherFragment"
        fun newInstance() = WeatherFragment()
    }

    private val presenter = WeatherPresenter(this)

    override fun onResume() {
        super.onResume()
        presenter.readPreference()
        btAddCity.setOnClickListener {
            if (!edCity.text.toString().isBlank()) {
                presenter.city = edCity.text.toString()
                edCity.text.clear()
            }
        }

        btRefresh.setOnClickListener {
            presenter.refreshData()
        }

    }

    override fun showInfo(city: String, temperature: String) {
        try {
            tvCity.text = city
            tvInfo.text = temperature
        } catch (e: Exception) {
        }
    }

    override fun getContext(): Context {
        return activity!!
    }

    override fun getLayout() = R.layout.weather_fragment

}
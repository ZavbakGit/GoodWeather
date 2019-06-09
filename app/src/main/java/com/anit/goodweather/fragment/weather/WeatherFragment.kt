package com.anit.goodweather.fragment.weather


import android.content.Context
import android.widget.ArrayAdapter
import com.anit.goodweather.R
import com.anit.goodweather.fragment.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : BaseFragment(), IWeatherView {


    companion object {
        const val TAG = "WeatherFragment"
        fun newInstance() = WeatherFragment()
    }

    private lateinit var presenter: WeatherPresenter

    override fun onResume() {
        super.onResume()

        presenter = WeatherPresenter(this, activity!!.applicationContext)

        presenter.readPreference()
        btAddCity.setOnClickListener {
            if (!actvCity.text.toString().isBlank()) {
                presenter.city = actvCity.text.toString()
                actvCity.text.clear()
            }
        }

        btRefresh.setOnClickListener {
            presenter.refreshData()
        }

        actvCity.setOnItemClickListener { _, _, _, _ ->
            presenter.city = actvCity.text.toString()
            actvCity.text.clear()
            presenter.refreshData()
        }
    }

    override fun setAdapterAutoComplete(list: List<String>) {
        activity?.let {
            val adapter = ArrayAdapter<String>(
                activity as Context,
                R.layout.support_simple_spinner_dropdown_item,
                list
            )
            actvCity.setAdapter<ArrayAdapter<String>>(adapter)
        }

    }

    override fun showInfo(city: String, temperature: String) {
        try {
            tvCity.text = city
            tvInfo.text = temperature
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun loadImage(path: String?) {
        try {
            Picasso.get()
                .load(path)
                .into(ivIcon)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getContext(): Context {
        return activity!!
    }

    override fun getLayout() = R.layout.weather_fragment

}
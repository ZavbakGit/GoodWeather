package com.anit.goodweather.fragment

import com.anit.goodweather.R


class AboutFragment: BaseFragment(){

    companion object {
        val TAG = "AboutFragment"
        fun newInstance() = AboutFragment()
    }

    override fun getLayout() = R.layout.about_fragment

}
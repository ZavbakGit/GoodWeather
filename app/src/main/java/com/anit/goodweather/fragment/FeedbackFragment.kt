package com.anit.goodweather.fragment

import com.anit.goodweather.R


class FeedbackFragment: BaseFragment(){

    companion object {
        val TAG = "FeedbackFragment"
        fun newInstance() = FeedbackFragment()
    }

    override fun getLayout() = R.layout.feedback_fragment

}
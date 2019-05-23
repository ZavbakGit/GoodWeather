package com.anit.goodweather.fragment

import android.widget.Toast
import com.anit.goodweather.R
import kotlinx.android.synthetic.main.feedback_fragment.*


class FeedbackFragment: BaseFragment(){

    companion object {
        val TAG = "FeedbackFragment"
        fun newInstance() = FeedbackFragment()
    }

    override fun getLayout() = R.layout.feedback_fragment

    override fun onStart() {
        super.onStart()
        button_send.setOnClickListener {
            edit_message.setText("")
            Toast.makeText(activity!!,"Спасибо за ваше сообщение!",Toast.LENGTH_SHORT)
                .show()
        }

    }

}
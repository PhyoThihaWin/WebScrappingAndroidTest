package com.novalabs.webscrappingtest

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class OTPTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otptest)

        findViewById<OtpTextView>(R.id.otp_view).otpListener = object : OTPListener {
            override fun onInteractionListener() {
                //
            }

            override fun onOTPComplete(otp: String) {
                Toast.makeText(this@OTPTestActivity, otp, Toast.LENGTH_SHORT).show()
            }

        }
    }
}
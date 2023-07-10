package com.sbz.zomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.hbb20.CountryCodePicker
import com.sbz.zomato.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var phoneNUmber: String
    private lateinit var countryCode: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView(this@LoginActivity, R.layout.activity_login)

        binding.btnContinue.setOnClickListener {
            goToOptVerification()
        }

        binding.tvForgotPassword.setOnClickListener {
            goToForgotPassword()
        }

        binding.ivBtnLoginGoogle.setOnClickListener {
            loginUsingGMail()
        }

        binding.btnMoreOptions.setOnClickListener {
            moreOptions()
        }
    }

    private fun moreOptions() {
        //write Logic for More options here
    }

    private fun loginUsingGMail() {
        //Logic To login using Gmail
    }

    private fun goToForgotPassword() {
        startActivity(Intent(this@LoginActivity, ForgotPassword::class.java))
        finish()
    }

    private fun goToOptVerification() {
        phoneNUmber = binding.tvPhoneNumber.text.toString()
        if (phoneNUmber.isNotEmpty()) {
            countryCode = binding.ccpCountryCode.selectedCountryCode.toString()
            val finalPhoneNumber = "+$countryCode-$phoneNUmber"
            Log.d("FINAL_PHONE_NUMBER", finalPhoneNumber)
            val intent = Intent(this@LoginActivity, VerifyOTP::class.java)
            intent.putExtra("finalPhoneNumber", finalPhoneNumber)
            startActivity(intent)

            finish()
        } else {
            Toast.makeText(this@LoginActivity, "Phone Number Can't be Empty", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
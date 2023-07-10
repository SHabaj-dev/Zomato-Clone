package com.sbz.zomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.sbz.zomato.databinding.ActivityLoginBinding
import com.sbz.zomato.databinding.ActivityVerifyOtpBinding

class VerifyOTP : AppCompatActivity() {
    private lateinit var finalPhoneNumber: String
    private lateinit var binding: ActivityVerifyOtpBinding
    private var remainingTimeInMillis: Long = 0
    private val totalTimeInMillis: Long = 60000
    private val intervalInMillis: Long = 1000
    private lateinit var countdownTimer: CountDownTimer
    private lateinit var getOTP: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp)
        startTimer(totalTimeInMillis)
        finalPhoneNumber = intent.getStringExtra("finalPhoneNumber").toString()
        if (finalPhoneNumber.isNotEmpty()) {
            binding.tvOtpPhoneNumber.text = finalPhoneNumber
        }

        binding.btnVerify.setOnClickListener {
            otpVerification()
        }

        binding.btnReSend.setOnClickListener {
            reSendOTP()
        }


    }

    private fun reSendOTP() {
        startTimer(totalTimeInMillis)
        binding.btnReSend.isEnabled = false
        binding.btnReSend.setTextColor(ContextCompat.getColor(this@VerifyOTP, R.color.gray))
    }

    private fun otpVerification() {
        getOTP = binding.pinview.text.toString()
        if (getOTP.length < 6 || getOTP.isEmpty()) {
            Toast.makeText(this, "Fields Can't be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (getOTP == "786786") {
                //Start Main Activity
                val intent = Intent(this@VerifyOTP, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "OTP verification Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun startTimer(totalTimeInMillis: Long) {
        countdownTimer = object : CountDownTimer(totalTimeInMillis, intervalInMillis) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTimeInMillis = millisUntilFinished
                updateTimerUI()
            }

            override fun onFinish() {
                remainingTimeInMillis = 0
                updateTimerUI()
            }
        }.start()
    }

    private fun updateTimerUI() {
        if (remainingTimeInMillis > 0) {
            val seconds = remainingTimeInMillis / 1000
            binding.btnReSend.isEnabled = false
            binding.btnReSend.setTextColor(ContextCompat.getColor(this@VerifyOTP, R.color.gray))
            binding.tvTimer.text = seconds.toString()
        } else {
            binding.btnReSend.isEnabled = true
            binding.btnReSend.setTextColor(ContextCompat.getColor(this@VerifyOTP, R.color.black))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countdownTimer.cancel()
    }
}
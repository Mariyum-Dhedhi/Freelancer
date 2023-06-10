package com.android.freelancer
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMER: Long = 10000
    private val TIMER_INTERVAL: Long = 1000

    lateinit var timeCount: TextView

    private lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timeCount = findViewById(R.id.timeCounter)


        countDownTimer = object : CountDownTimer(SPLASH_TIMER, TIMER_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                timeCount.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                navigateToHome()
            }
        }

        countDownTimer.start()

        val skipButton = findViewById<Button>(R.id.skipButton)
        skipButton.setOnClickListener {
            // Cancel the timer and navigate to the next activity immediately
            countDownTimer.cancel()
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Close the splash activity to prevent going back to it
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}
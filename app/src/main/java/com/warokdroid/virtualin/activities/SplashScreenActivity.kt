package com.warokdroid.virtualin.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.warokdroid.virtualin.R

class SplashScreenActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_TIME_OUT: Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Using Handler to wait the next activity
        Handler().postDelayed({

            // Create intent object to move into LoginActivity
            val loginIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(loginIntent)

            // Call finish() function to destroy latest activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}

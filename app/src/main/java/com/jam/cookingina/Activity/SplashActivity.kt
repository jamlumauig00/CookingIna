package com.jam.cookingina.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.jam.cookingina.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        startSplash()
    }

    private fun startSplash() {
        val handler = Handler()
            handler.postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
    }
}
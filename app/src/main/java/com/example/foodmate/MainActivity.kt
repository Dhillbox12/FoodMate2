package com.example.foodmate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import activity.SplashActivty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, SplashActivty::class.java))
        finish()
    }
}

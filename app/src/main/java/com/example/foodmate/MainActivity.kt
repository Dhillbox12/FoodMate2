package com.example.foodmate

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import activity.SplashActivty

/**
 * Entry point — langsung redirect ke SplashActivity.
 * MainActivity dipertahankan agar tidak perlu ubah AndroidManifest launcher.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, SplashActivty::class.java))
        finish()
    }
}

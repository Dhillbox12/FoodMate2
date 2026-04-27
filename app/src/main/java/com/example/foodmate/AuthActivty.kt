package com.example.foodmate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import activity.HomeActivty
import com.example.foodmate.databinding.ActivityAuthBinding

class AuthActivty : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTabs()
        setupLoginForm()
        setupRegisterForm()
    }

    private fun setupTabs() {
        binding.tvTabLogin.setOnClickListener {
            binding.layoutLogin.visibility = android.view.View.VISIBLE
            binding.layoutRegister.visibility = android.view.View.GONE
            binding.tvTabLogin.setBackgroundResource(R.drawable.bg_tab_selected)
            binding.tvTabLogin.setTextColor(resources.getColor(R.color.text_white, null))
            binding.tvTabRegister.setBackgroundResource(R.drawable.bg_tab_unselected)
            binding.tvTabRegister.setTextColor(resources.getColor(R.color.text_secondary, null))
        }

        binding.tvTabRegister.setOnClickListener {
            binding.layoutLogin.visibility = android.view.View.GONE
            binding.layoutRegister.visibility = android.view.View.VISIBLE
            binding.tvTabRegister.setBackgroundResource(R.drawable.bg_tab_selected)
            binding.tvTabRegister.setTextColor(resources.getColor(R.color.text_white, null))
            binding.tvTabLogin.setBackgroundResource(R.drawable.bg_tab_unselected)
            binding.tvTabLogin.setTextColor(resources.getColor(R.color.text_secondary, null))
        }
    }

    private fun setupLoginForm() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val password = binding.etLoginPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan kata sandi tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8) {
                Toast.makeText(this, "Kata sandi minimal 8 karakter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            navigateToHome()
        }

        binding.btnLoginGoogle.setOnClickListener {
            navigateToHome()
        }
    }

    private fun setupRegisterForm() {
        binding.btnRegister.setOnClickListener {
            val name = binding.etRegisterName.text.toString().trim()
            val email = binding.etRegisterEmail.text.toString().trim()
            val phone = binding.etRegisterPhone.text.toString().trim()
            val password = binding.etRegisterPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8) {
                Toast.makeText(this, "Kata sandi minimal 8 karakter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Akun berhasil dibuat! Silakan masuk.", Toast.LENGTH_SHORT).show()
            binding.tvTabLogin.performClick()
        }

        binding.btnRegisterGoogle.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivty::class.java))
        finish()
    }
}

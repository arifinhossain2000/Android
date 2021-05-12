package com.example.kotlin3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin3.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginbutton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("database",Context.MODE_PRIVATE)
            val edit = sharedPreferences.edit()
            edit.apply{
                putString("status","login")
            }.apply()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}
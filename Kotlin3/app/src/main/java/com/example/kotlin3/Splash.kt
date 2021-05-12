package com.example.kotlin3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("database", Context.MODE_PRIVATE)

        if(sharedPreferences.contains("status")){
            val value=sharedPreferences.getString("status","Not login")
            if (value == "login"){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else if (value == "Not login"){
                startActivity(Intent(this,Login::class.java))
                finish()
            }
        }
        else{
            startActivity(Intent(this,Login::class.java))
            finish()
        }


    }
}
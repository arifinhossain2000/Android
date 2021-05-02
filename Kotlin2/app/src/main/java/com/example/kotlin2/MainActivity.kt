package com.example.kotlin2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.kotlin2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun gorecycle(view: View) {
        startActivity(Intent(this,Recycle::class.java))
    }
    fun gogrid(view: View) {
        startActivity(Intent(this,Graid::class.java))
    }
}
package com.example.kotlin_homework1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bdButton.setOnClickListener(this)
        indButton.setOnClickListener(this)
        pakButton.setOnClickListener(this)
        srikButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.bdButton-> {
                val intent = Intent(this,detalis::class.java)
                startActivity(intent)
            }
            R.id.indButton->{
                val intent = Intent(this,detalis::class.java)
                startActivity(intent)
            }
            R.id.pakButton->{
                val intent = Intent(this,detalis::class.java)
                startActivity(intent)
            }
            R.id.srikButton->{

                val intent = Intent(this,detalis::class.java)
                startActivity(intent)
            }
        }

    }


}
package com.example.kotlin3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin3.databinding.ActivityInsertBinding
import com.google.android.material.snackbar.Snackbar

class Insert : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db= DBhelper(this)

        binding.saveButton.setOnClickListener{

            val name = binding.nameEdit.text.toString()
            val mail = binding.mailEdit.text.toString()

            if (name.isNotEmpty() && mail.isNotEmpty()){
                val value = db.insert(name,mail)
                if (value == (-1).toLong()){

                    Snackbar.make(binding.insertMain,"Insert failed",Snackbar.LENGTH_SHORT).show()
                }
                else{

                    Snackbar.make(binding.insertMain,"Insert Sucessfull",Snackbar.LENGTH_SHORT).show()

                }

            }
            else{
                Snackbar.make(binding.insertMain,"Please insert data",Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.viewButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
//        binding.viewButton.setOnClickListener {
//            val number= binding.phoneEditText.text.toString().trim()
//            if(number.isNotEmpty()){
//                val intent= Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
//                startActivity(intent)
//            }
//            else{
//                Snackbar.make(binding.insertMain,"Please enter phone",Snackbar.LENGTH_SHORT).show()
//
//            }
//        }
    }
}
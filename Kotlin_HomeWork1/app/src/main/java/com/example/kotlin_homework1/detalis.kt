package com.example.kotlin_homework1

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_detalis.*
import kotlinx.android.synthetic.main.activity_detalis.*
import java.net.URI

class detalis : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalis)

        pickImage.setOnClickListener(this)
    }



    override fun onClick(view: View?) {
        when(view?.id){
            R.id.pickImage-> {
                openDialogue()

            }
        }


    }

    private fun openDialogue() {
        val dialogue= AlertDialog.Builder(this)
        val option= arrayOf("Gallery","Camera")
        dialogue.setTitle("Choose a option")
        dialogue.setItems(option){ _,which ->
            val selected= option[which]

            if(selected=="Gallery"){
                openGallery()
                // Toast.makeText(this,selected,Toast.LENGTH_LONG).show()
            }
            else{
                openCamera()
                //Toast.makeText(this,selected,Toast.LENGTH_LONG).show()
            }

        }

        val alert= dialogue.create().show()
    }

    private fun openCamera() {

        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,2)


    }

    private fun openGallery() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1 && resultCode== RESULT_OK){

            pickImage.setImageURI(data?.data)
        }
        else if (requestCode==2 && resultCode== RESULT_OK){

            val bitmap = data?.extras?.get("data") as Bitmap
            pickImage.setImageBitmap(bitmap)
        }
    }


}

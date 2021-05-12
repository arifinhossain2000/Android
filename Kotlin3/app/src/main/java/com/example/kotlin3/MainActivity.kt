package com.example.kotlin3

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin3.databinding.ActivityMainBinding
import com.example.kotlin3.databinding.UpdatedailougeBinding
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class MainActivity : AppCompatActivity(),View.OnClickListener,MyAdapter.onItemClick{

     lateinit var dBhelper: DBhelper
     lateinit var adapter: MyAdapter
     private var userList=ArrayList<User>()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title= "User List"
        binding.insertButton.setOnClickListener(this)
        binding.logoutButton.setOnClickListener(this)
        binding.recycleId.layoutManager = LinearLayoutManager(this)
        binding.recycleId.setHasFixedSize(true)

        loadData()
    }

    private fun loadData() {

        dBhelper = DBhelper(this)
        val cursor =dBhelper.show()

        if(cursor.count==0){
            Snackbar.make(binding.mainId,"No data found",Snackbar.LENGTH_SHORT).show()
        }
        else{

            while (cursor.moveToNext()){
                val id= cursor.getString( 0).toString()
                val name= cursor.getString(1).toString()
                val email= cursor.getString(2).toString()
                val user= User(id,name, email)
                userList.add(user)
            }
            adapter= MyAdapter(userList,this)
            binding.recycleId.adapter= adapter
        }

    }

    override fun onClick(view: View?) {

        when(view?.id){
            R.id.insertButton ->{
                startActivity(Intent(this,Insert::class.java))


            }
            R.id.logoutButton ->{
                val sharedPreferences = getSharedPreferences("database",Context.MODE_PRIVATE)
                val edit = sharedPreferences.edit()
                edit.apply{
                    putString("status","not login")
                }.apply()

                val intent = Intent(this, Login::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

            }
        }

    }

    override fun onItemClick(position: Int) {
        Snackbar.make(binding.mainId,""+userList[position].name,Snackbar.LENGTH_SHORT).show()

    }

    override fun onLongClick(i: Int) {

        val dialogue= AlertDialog.Builder(this)
        val  option= arrayOf("Update","Delete")
        dialogue.setTitle("Choose a option")
        dialogue.setItems(option){ dialogInterface: DialogInterface, i: Int ->

            val select= option[i]
            if(select=="Update"){
                upData(i)

            }
            else{
                val id= userList[i].id
                val value= dBhelper.delete(id)
                if(value>0){
                    userList.removeAt(i)
                    adapter.notifyItemRemoved(i)
                    Snackbar.make(binding.mainId,"Delete Successfully",Snackbar.LENGTH_SHORT).show()

                }
                else{
                    Snackbar.make(binding.mainId,"Delete Failed",Snackbar.LENGTH_SHORT).show()

                }

            }
        }
        val alert= dialogue.create().show()

    }

    private fun upData(i: Int) {

        val dailog = AlertDialog.Builder(this)
        val view = UpdatedailougeBinding.inflate(LayoutInflater.from(this))
        dailog.setView(view.root).setTitle("Update").setTitle("Update").setCancelable(true)
            .setPositiveButton("Update"){ dialogInterface: DialogInterface, i: Int ->

                val  id = userList[i].id
                val name = view.editName.text.toString()
                val mail = view.editMail.text.toString()
                val user = User(id,name, mail)
                val value = dBhelper.update(user)

                if(value> 0 ){
                    Snackbar.make(binding.mainId,"Update Done",Snackbar.LENGTH_SHORT).show()
                }
                else{
                    Snackbar.make(binding.mainId,"Update Failed",Snackbar.LENGTH_SHORT).show()
                }
                

            }.setNegativeButton("Close"){ dialogInterface: DialogInterface, i: Int ->

            }
        view.textId.text ="Updating index no ${userList[i].id}"
        view.editName.setText(userList[i].name)
        view.editMail.setText(userList[i].mail)

        dailog.create().show()




    }
}
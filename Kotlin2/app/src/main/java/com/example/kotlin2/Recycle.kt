package com.example.kotlin2


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin2.databinding.ActivityMainBinding
import com.example.kotlin2.databinding.ActivityRecycleBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycle.*
import kotlin.random.Random

class Recycle : AppCompatActivity() {

    private val image= arrayOf(R.drawable.messi,R.drawable.shakib,R.drawable.neymar,
        R.drawable.mushfiqur,R.drawable.tamim, R.drawable.virat ,R.drawable.mark,R.drawable.jamal,R.drawable.siddikur)

       private val playerList= ArrayList<Player>()
       lateinit var adapter:MyAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)
        setTitle("Recycle View")

        val playerName= resources.getStringArray(R.array.player)
        val playerRole= resources.getStringArray(R.array.role)

        for (i in playerName.indices){
            val player= Player(image[i],playerName[i],playerRole[i])
            playerList.add(player)
        }
        adapter= MyAdapter(playerList)
        recycleView.layoutManager= LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)
        recycleView.adapter= adapter

        recycleRefresh.setOnRefreshListener {
            val index= Random.nextInt(9)
            val player= Player(image[index],playerName[index],playerRole[index])
            playerList.add(player)
            adapter.notifyDataSetChanged()
            recycleRefresh.isRefreshing= false
        }

    }

//    override fun onItemClick(position: Int) {
//
//        val value= playerList[position].name.toString()
//        Snackbar.make(recycleLayout,value,Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onLongItemClick(position: Int) {
//
//        val builder= AlertDialog.Builder(this)
//        builder.setTitle("Alert")
//        builder.setMessage("Do you want to delete ?")
//        builder.setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
//
//            playerList.removeAt(position)
//            adapter.notifyItemRemoved(position)
//        }
//        builder.setNegativeButton("No",null)
//        val dialoge= builder.create()
//        dialoge.show()
//    }
}
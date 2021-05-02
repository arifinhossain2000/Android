package com.example.kotlin2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_graid.*
import kotlinx.android.synthetic.main.activity_recycle.*
import kotlin.random.Random

class Graid : AppCompatActivity() {

    private val image= arrayOf(R.drawable.messi,R.drawable.shakib,R.drawable.neymar,
            R.drawable.mushfiqur,R.drawable.tamim, R.drawable.virat ,R.drawable.mark,R.drawable.jamal,R.drawable.siddikur)

    private val playerList= ArrayList<Player>()
    private lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graid)

        setTitle("Grid View")

        val playerName= resources.getStringArray(R.array.player)
        val playerRole= resources.getStringArray(R.array.role)

        for (i in playerName.indices){
            val player= Player(image[i],playerName[i],playerRole[i])
            playerList.add(player)
        }
        adapter= GridAdapter(playerList,this)
        gridView.adapter=adapter

        gridSwip.setOnRefreshListener {

            val index= Random.nextInt(9)
            val player= Player(image[index],playerName[index],playerRole[index])
            playerList.add(player)
            adapter.notifyDataSetChanged()
            gridSwip.isRefreshing= false

        }

    }
}
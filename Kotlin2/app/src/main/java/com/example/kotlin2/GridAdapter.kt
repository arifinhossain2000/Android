package com.example.kotlin2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.security.AccessControlContext

class GridAdapter(private val playerList:List<Player>, private val context:Context):BaseAdapter() {

    var layoutInflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if(view== null){
            view=layoutInflater.inflate(R.layout.single_grid_item,parent,false)

        }
        val image:ImageView? = view?.findViewById(R.id.gridSingleImage)
        val name:TextView?= view?.findViewById(R.id.gridSingleName)
        val roll:TextView?= view?.findViewById(R.id.gridSingleRoll)

        image?.setImageResource(playerList[position].image)
        name?.text = (playerList[position].Name)
        roll?.text=(playerList[position].Roll)


        return  view!!


    }

    override fun getCount(): Int {
        return playerList.size
    }

    override fun getItem(position: Int): Any {
        return playerList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



}
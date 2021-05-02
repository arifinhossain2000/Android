package com.example.kotlin2


import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.single_recycle_item.view.*

class MyAdapter(private val playerList:List<Player>, private val listner:OnItmClick): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.single_recycle_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.image.setImageResource(playerList[position].image)
        holder.name.text= playerList[position].Name
        holder.role.text= playerList[position].Roll
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    // override fun getItemCount()= playerList.size

    inner  class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener {

        val image: CircleImageView = itemView.singleRecycleImage
        val name: TextView = itemView.singleRecycleName
        val role: TextView = itemView.singleRecycleRole

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
        override fun onClick(v: View?) {
            val position:Int = adapterPosition

            if (position != RecyclerView.NO_POSITION){
                listner.onItemClick(position)

            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position:Int = adapterPosition

            if (position != RecyclerView.NO_POSITION){
                listner.onLongItemClick(position)

            }
          return false
        }

    }

    interface  OnItmClick{

        fun onItemClick(position:Int)
        fun onLongItemClick(position:Int)
    }
}
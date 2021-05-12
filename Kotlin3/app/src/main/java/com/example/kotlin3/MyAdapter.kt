package com.example.kotlin3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin3.databinding.SingleItemBinding

class MyAdapter(private  val userList:List<User>, private val listener:onItemClick):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = SingleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.name.text = userList[position].name
        holder.email.text = userList[position].mail

    }

    override fun getItemCount() = userList.size

    inner  class MyViewHolder(private val binding: SingleItemBinding):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener,View.OnLongClickListener{

        val name:TextView = binding.singleName
        val email:TextView = binding.singleMail

        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            val position:Int = adapterPosition

            if (position != RecyclerView.NO_POSITION){

                listener.onItemClick(position)
            }
        }

        override fun onLongClick(v: View?): Boolean {
            val position:Int = adapterPosition

            if (position != RecyclerView.NO_POSITION){

                listener.onLongClick(position)
            }
            return false
        }
    }

    interface onItemClick{
        fun onItemClick(i:Int)
        fun onLongClick(i:Int)
    }
}
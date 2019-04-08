package com.example.kanwal_laptop.lecture09_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class myCustomAdapter (context : Context,
                       val userData : ArrayList<String>) : RecyclerView.Adapter<myCustomAdapter.viewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val rowView : View = LayoutInflater.from(parent.context).inflate(R.layout.my_custom_layout,
                                                    parent, false)
        return viewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return userData.size
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.displayUserName.text = userData[position]
    }

    class viewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val displayUserName : TextView = itemView.findViewById(R.id.display_name)
    }
}
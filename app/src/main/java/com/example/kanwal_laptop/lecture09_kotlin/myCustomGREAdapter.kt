package com.example.kanwal_laptop.lecture09_kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class myCustomGREAdapter (context : Context,
                          val greWordsList : ArrayList<String>,
                          var onItemClick : (Int) ->Unit = {}) : RecyclerView.Adapter<myCustomGREAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.my_custom_layout,
            parent, false
        )
        return viewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return greWordsList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.displayGREWord.text = greWordsList[position]
        holder.myContainer.setOnClickListener {
            onItemClick(position)
        }
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val displayGREWord: TextView = itemView.findViewById(R.id.display_name)
        val myContainer : View = itemView.findViewById(R.id.container_root)
    }
}
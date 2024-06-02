package com.mistershorr.loginandregistration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReplyAdapter (var dataSet: MutableList<Response>) : RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reply, parent, false)
        val holder = ViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: ReplyAdapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
package com.mistershorr.loginandregistration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ConversationAdapter (var dataSet: MutableList<Conversation>) : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        val holder = ViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: ConversationAdapter.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    }
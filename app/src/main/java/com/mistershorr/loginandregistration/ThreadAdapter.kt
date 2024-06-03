package com.mistershorr.loginandregistration

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ThreadAdapter(var dataSet: MutableList<Thread?>?) : RecyclerView.Adapter<ThreadAdapter.ViewHolder>() {

    companion object {
        val TAG = "ThreadAdapter"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTopic : TextView
        val textViewDescription: TextView
        val layout: ConstraintLayout

        init {
            textViewTopic = view.findViewById(R.id.textView_thread_topic)
            textViewDescription = view.findViewById(R.id.textView_thread_description)
            layout = view.findViewById(R.id.layout_itemThread)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_thread, parent, false)
        val holder = ViewHolder(view)
        return holder
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val thread = dataSet?.get(position)


        val context = viewHolder.layout.context
        viewHolder.textViewTopic.text = dataSet?.get(position)?.title.toString()
        viewHolder.textViewDescription.text = dataSet?.get(position)?.description.toString()
        viewHolder.layout.setOnClickListener {
            val intent = Intent(context, ConversationListActivity::class.java).apply {
                putExtra(ConversationListActivity.HELLO_THERE, thread)
            }
                intent.putExtra("threadID", thread?.objectId)

            Log.d(TAG, intent.toString())
//            intent.putExtra("sleepDate",sleepDate.toString())
//            intent.putExtra("bedTime",bedTime.toString())
//            intent.putExtra("wakeTime",wakeTime.toString())
//            intent.putExtra("rating",holder.ratingBarQuality.rating.toString())
//            intent.putExtra("objectId", Backendless.UserService.CurrentUser().objectId)
            context.startActivity(intent)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int{

            return dataSet!!.size


}
}
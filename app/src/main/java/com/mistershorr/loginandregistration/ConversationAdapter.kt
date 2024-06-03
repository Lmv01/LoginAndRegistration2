package com.mistershorr.loginandregistration

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault

class ConversationAdapter (var dataSet: MutableList<Conversation>) : RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {
    companion object {
        val TAG1 = "ThreadAdapter"
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var  description : TextView
        lateinit var name : TextView
        lateinit var upvotes: TextView
        lateinit var downvotes : TextView
        lateinit var layout : ConstraintLayout
        lateinit var upvoteCounter : ImageButton
        lateinit var downvoteCounter : ImageButton
        init {
            description = view.findViewById(R.id.textView_conversation_description)
            name = view.findViewById(R.id.textView_Conversation_Title)
            upvotes = view.findViewById(R.id.textView_conversation_upvoteCount)
            downvotes = view.findViewById(R.id.textView_conversation_downvoteCount)
            layout = view.findViewById(R.id.layout_Conversation)
            upvoteCounter = view.findViewById(R.id.imageButton_reply_upvote)
            downvoteCounter = view.findViewById(R.id.imageButton_conversatoin_downvote)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        val holder = ViewHolder(view)
        return holder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conversation = dataSet?.get(position)


        val context = holder.layout.context
        holder.layout.isLongClickable = true
        holder.layout.setOnLongClickListener {
            // the holder.textViewBorrower is the textView that the PopMenu will be anchored to
            val popMenu = PopupMenu(context, holder.description)
            popMenu.inflate(R.menu.menu_thing)
            popMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_delete -> {
                        deleteFromBackendless(position)
                        true
                    }
                    else -> true
                }
            }
            popMenu.show()
            true
        }
        holder.name.text = dataSet?.get(position)?.title.toString()
        holder.description.text = dataSet?.get(position)?.description.toString()
        holder.upvotes.text = dataSet?.get(position)?.upvotes.toString()
        holder.downvotes.text = dataSet?.get(position)?.downvotes.toString()
        holder.downvoteCounter.setOnClickListener {

            dataSet?.get(position)?.downvotes ++

            Backendless.Data.of(Conversation::class.java).save(conversation, object : AsyncCallback<Conversation?>{
                override fun handleResponse(response: Conversation?) {
                    notifyDataSetChanged()
                }

                override fun handleFault(fault: BackendlessFault?) {
                    if (fault != null) {
                        fault.getCode()
                    }
                }
            })
        }
        holder.upvoteCounter.setOnClickListener {

            dataSet?.get(position)?.upvotes ++

            Backendless.Data.of(Conversation::class.java).save(conversation, object : AsyncCallback<Conversation?>{
                override fun handleResponse(response: Conversation?) {
                    notifyDataSetChanged()
                }

                override fun handleFault(fault: BackendlessFault?) {
                    if (fault != null) {
                        fault.getCode()
                    }
                }
            })
        }
        holder.name.setOnClickListener {
                val intent = Intent(context, ReplyListActivity::class.java).apply {
                putExtra(ReplyListActivity.HI_THERE, conversation)
            }
            intent.putExtra("poo", conversation?.objectId)

            Log.d(ConversationAdapter.TAG1, intent.toString())
//            intent.putExtra("sleepDate",sleepDate.toString())
//            intent.putExtra("bedTime",bedTime.toString())
//            intent.putExtra("wakeTime",wakeTime.toString())
//            intent.putExtra("rating",holder.ratingBarQuality.rating.toString())
//            intent.putExtra("objectId", Backendless.UserService.CurrentUser().objectId)
            context.startActivity(intent)
        }
    }
    private fun deleteFromBackendless(position: Int) {
        Backendless.Data.of(Conversation::class.java).remove(dataSet[position],object : AsyncCallback<Long?>{
            override fun handleResponse(response: Long?) {
                dataSet.removeAt(position)
                notifyDataSetChanged()
            }

            override fun handleFault(fault: BackendlessFault?) {
                TODO("Not yet implemented")
            }

        }
        )

        // put in the code to delete the item using the callback from Backendless
        // in the handleResponse, we'll need to also delete the item from the sleepList
        // and make sure that the recyclerview is updated
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    }
package com.mistershorr.loginandregistration

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.mistershorr.loginandregistration.databinding.ActivityConversationListBinding
import com.mistershorr.loginandregistration.databinding.ActivityThreadListBinding

class ConversationListActivity : AppCompatActivity() {
    companion object{
        val HELLO_THERE = "wellhellothere"
    }

    private lateinit var binding: ActivityConversationListBinding
    private lateinit var adapter: ConversationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConversationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadDataFromBackendless3()
        binding.addButtonConversationListAddConversation.setOnClickListener {
            val threadID = intent.extras?.get("threadID")
            val intent = Intent(this, ConversationDetailActivity::class.java)
            intent.putExtra("IHATELIFE", threadID.toString())
            this.startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        loadDataFromBackendless3()
    }

    fun loadDataFromBackendless3() {
        val userId = Backendless.UserService.CurrentUser().userId
        // need the ownerId to match the objectId of the user
        val whereClause = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.whereClause = whereClause
        // include the queryBuilder in the find function
        Backendless.Data.of(Conversation::class.java).find(queryBuilder,object :
            AsyncCallback<List<Conversation>> {
            override fun handleResponse(response: List<Conversation>?) {
                var conversationList : List<Conversation>?


                    val threadID = intent.extras?.get("threadID")
                    conversationList = response?.filter {it.threadId == threadID.toString()}

                val adapter = ConversationAdapter(conversationList as MutableList<Conversation>)
                binding.recyclerViewConversationListListOfConversations.adapter = adapter
                binding.recyclerViewConversationListListOfConversations.layoutManager = LinearLayoutManager(this@ConversationListActivity)
            }

            override fun handleFault(fault: BackendlessFault?) {
                TODO("Not yet implemented")
            }

        })}
}
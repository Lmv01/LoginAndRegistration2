package com.mistershorr.loginandregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mistershorr.loginandregistration.databinding.ActivityReplyListBinding
import com.mistershorr.loginandregistration.databinding.ActivityThreadListBinding


class ReplyListActivity : AppCompatActivity() {
    companion object {
        val HI_THERE = "why hi there"
    }
    lateinit var binding : ActivityReplyListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReplyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadDataFromBackendless3()
        binding.addButtonReplyLIstAddReply.setOnClickListener{
            val conversationID = intent.extras?.get("poo")
            val intent = Intent(this, ReplyDetailActivity::class.java)
            intent.putExtra("hello", conversationID.toString())
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
        Backendless.Data.of(Response::class.java).find(queryBuilder,object :
            AsyncCallback<List<Response>> {
            override fun handleResponse(response: List<Response>?) {
                var responseList : List<Response>?


                val conversationID = intent.extras?.get("poo")
                responseList = response?.filter {it.conversationID == conversationID.toString() }


                val adapter = ReplyAdapter(responseList as MutableList<Response>)
                binding.recyclerViewReplyListOfReplies.adapter = adapter
                binding.recyclerViewReplyListOfReplies.layoutManager = LinearLayoutManager(this@ReplyListActivity)
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.d("HI_IMHERE","${fault?.message}")
            }

        })}
}
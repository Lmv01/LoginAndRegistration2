package com.mistershorr.loginandregistration

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import com.mistershorr.loginandregistration.databinding.ActivityLoginBinding
import com.mistershorr.loginandregistration.databinding.ActivityThreadListBinding


class ThreadListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadListBinding
    private lateinit var adapter: ThreadAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadDataFromBackendless2()

    }

    override fun onResume() {
        super.onResume()
        loadDataFromBackendless2()
    }


    private fun loadDataFromBackendless2() {
        val userId = Backendless.UserService.CurrentUser().userId
        // need the ownerId to match the objectId of the user
        val whereClause = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.whereClause = whereClause
        // include the queryBuilder in the find function
        Backendless.Data.of(Thread::class.java).find(queryBuilder, object :
            AsyncCallback<List<Thread?>?> {
            override fun handleResponse(threadList: List<Thread?>?) {
                Log.d(ContentValues.TAG, "handleResponse: $threadList")
                adapter = ThreadAdapter(threadList?.toMutableList())
                binding.recyclerViewList.adapter = adapter
                binding.recyclerViewList.layoutManager = LinearLayoutManager(this@ThreadListActivity)


                // this is where you would set up your recyclerView
            }
            override fun handleFault(fault: BackendlessFault) {
                Log.d(ContentValues.TAG, "handleFault: ${fault.message}")
            }
        })
    }
}
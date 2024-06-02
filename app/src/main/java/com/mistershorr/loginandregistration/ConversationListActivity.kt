package com.mistershorr.loginandregistration

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mistershorr.loginandregistration.databinding.ActivityConversationListBinding

class ConversationListActivity : AppCompatActivity() {
    companion object{
        val HELLO_THERE = "wellhellothere"
    }
    private lateinit var binding: ActivityConversationListBinding
    private lateinit var adapter: ConversationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_list)
    }}
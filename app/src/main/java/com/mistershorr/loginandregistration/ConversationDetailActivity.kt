package com.mistershorr.loginandregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.mistershorr.loginandregistration.databinding.ActivityConversationDetailBinding
import com.mistershorr.loginandregistration.databinding.ActivityConversationListBinding

class ConversationDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityConversationDetailBinding
    lateinit var text : String
    lateinit var text2 : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            text = binding.editTextName.text.toString()
            text2 = binding.editTextDescription1.text.toString()
            val poo = intent.extras?.get("IHATELIFE").toString()
            val newEntry = Conversation(text2,0,poo,text,0, null,Backendless.UserService.CurrentUser().userId)
            Backendless.Data.of(Conversation::class.java).save(newEntry, object :
                AsyncCallback<Conversation?> {
                override fun handleResponse(response: Conversation?) {
                    finish()
                }

                override fun handleFault(fault: BackendlessFault?) {
                    fault?.getMessage()
                }

            })



        }
        binding.button2.setOnClickListener {
            finish()
        }
    }
}
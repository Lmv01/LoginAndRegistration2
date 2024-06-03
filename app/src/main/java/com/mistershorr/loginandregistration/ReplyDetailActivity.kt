package com.mistershorr.loginandregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.mistershorr.loginandregistration.databinding.ActivityReplyDetailBinding

class ReplyDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityReplyDetailBinding
    lateinit var text : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReplyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.textViewResponseDEtailText3.setOnClickListener {
            text = binding.textViewResponseDEtailText3.toString()
        }*/
        binding.buttonReplyDetailSave.setOnClickListener{
            text = binding.textViewResponseDEtailText3.text.toString()
            val poo = intent.extras?.get("hello").toString()
            val newEntry = Response(0,text,0, poo,null,Backendless.UserService.CurrentUser().userId)
            Backendless.Data.of(Response::class.java).save(newEntry, object : AsyncCallback<Response?>{
                override fun handleResponse(response: Response?) {
                    finish()
                }

                override fun handleFault(fault: BackendlessFault?) {
                    fault?.getMessage()
                }

            })



        }
        binding.buttonReplyDetailCancel.setOnClickListener {
            finish()
        }
    }
}
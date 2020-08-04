package com.example.receiverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var displayText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayText =
            if (intent.action == "android.intent.action.MAIN") "Hello World! Send text from sender app!" else {
                intent.getStringExtra("com.example.senderapp.sender_text").toString()
            }

        val senderTextView = findViewById<TextView>(R.id.senderText)
        senderTextView.text = displayText
    }
}
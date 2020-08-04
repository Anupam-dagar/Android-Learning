package com.example.secondreceiverapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var displayText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayText =
                if (intent.action == "android.intent.action.MAIN") "Hello! Send text from sender app!" else {
                    intent.getStringExtra("com.example.senderapp.sender_text").toString()
                }

        val senderTextView = findViewById<TextView>(R.id.received_text)
        senderTextView.text = displayText
    }
}
package com.example.intentfiltersexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.send_email)
        sendButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("message/rfc822")
            intent.putExtra(Intent.EXTRA_EMAIL, "testemail@testing.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Testing an intent filter")
            intent.putExtra(Intent.EXTRA_TEXT, "Hello, this is a test of intent filters.")
            startActivity(Intent.createChooser(intent, "Select an email app"))
        }
    }
}
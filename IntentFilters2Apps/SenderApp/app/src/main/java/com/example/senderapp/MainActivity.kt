package com.example.senderapp

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun explicitSendTextToReceiverApp(view: View) {
        val sendTextField = findViewById<EditText>(R.id.editSendText)
        val message = sendTextField.text.toString()

        val intent = Intent("com.example.receiverapp.SENDERAPP_TEXT")
        intent.putExtra("com.example.senderapp.sender_text", message)
        intent.component = ComponentName("com.example.receiverapp", "com.example.receiverapp.MainActivity")
        startActivity(intent)
    }

    fun implicitSendTextToReceiverApp(view: View) {
        val sendTextField = findViewById<EditText>(R.id.editSendText)
        val message = sendTextField.text.toString()

        val intent = Intent("com.example.receiverapp.SENDERAPP_TEXT")
        intent.type = "text/plain"
        intent.putExtra("com.example.senderapp.sender_text", message)
        startActivity(Intent.createChooser(intent,"Select an app to continue"))
    }
}
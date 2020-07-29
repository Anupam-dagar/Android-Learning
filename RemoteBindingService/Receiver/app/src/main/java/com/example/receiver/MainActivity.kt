package com.example.receiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(applicationContext, RandomNumberService::class.java)
    }

    fun startRemoteService(view: View) {
        startService(serviceIntent)
    }

    fun stopRemoteService(view: View) {
        stopService(serviceIntent)
    }
}
package com.example.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var serviceIntent: Intent

    private lateinit var randomNumberService: RandomNumberService
    private var isRandomNumberServiceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as RandomNumberService.RandomNumberServiceBinder
            randomNumberService = binder.getService()
            isRandomNumberServiceBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isRandomNumberServiceBound = false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent(applicationContext, RandomNumberService::class.java)
    }

    fun serviceStarter(view: View) {
        startService(serviceIntent)
    }

    fun serviceStopper(view: View) {
        stopService(serviceIntent)
    }

    fun serviceBinder(view: View) {
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun serviceUnbinder(view: View) {
        if (isRandomNumberServiceBound) {
            unbindService(serviceConnection)
            isRandomNumberServiceBound = false
        }
    }

    fun showRandomNumber(view: View) {
        val displayView = findViewById<TextView>(R.id.randomNumberTextView)
        displayView.text = if (isRandomNumberServiceBound) randomNumberService.getRandomNumber()
            .toString() else "Service not bound, generate a random number."
    }
}
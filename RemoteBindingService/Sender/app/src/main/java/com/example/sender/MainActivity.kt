package com.example.sender

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var serviceIntent: Intent
    private var randomNumber = 0
    private var isRandomNumberServiceBound = false

    private val GET_RANDOM_NUMBER = 0

    private var receiveMessenger: Messenger? = null
    private var requestMessenger: Messenger? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceIntent = Intent().setComponent(
            ComponentName(
                "com.example.receiver",
                "com.example.receiver.RandomNumberService"
            )
        )
    }

    inner class ReceiveRandomNumberHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                GET_RANDOM_NUMBER -> {
                    randomNumber = msg.arg1
                    val displayView = findViewById<TextView>(R.id.displayTextView)
                    displayView.text = randomNumber.toString()
                }
                else -> super.handleMessage(msg)
            }
            super.handleMessage(msg)
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            requestMessenger = Messenger(p1)
            receiveMessenger = Messenger(ReceiveRandomNumberHandler(this@MainActivity))
            isRandomNumberServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            requestMessenger = null
            receiveMessenger = null
            isRandomNumberServiceBound = false
        }
    }

    fun bindRemoteService(view: View) {
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        Toast.makeText(applicationContext, "Remote Service Bound", Toast.LENGTH_SHORT).show()
    }

    fun unbindRemoteService(view: View) {
        unbindService(serviceConnection)
        isRandomNumberServiceBound = false
        Toast.makeText(applicationContext, "Remote Service Unbound", Toast.LENGTH_SHORT).show()
    }

    fun getRandomNumber(view: View) {
        if (isRandomNumberServiceBound) {
            val requestMessage = Message.obtain(null, GET_RANDOM_NUMBER)
            requestMessage.replyTo = receiveMessenger
            try {
                requestMessenger?.send(requestMessage)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Service Unbound, please bind the service first.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
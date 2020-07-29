package com.example.receiver

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

private val MSG_RANDOM_NUMBER = 0

class RandomNumberService : Service() {
    private val TAG = "RandomNumberService"
    private var randomNumber = 0
    private var isRandomNumberGeneratorInUse = false

    private val MIN = 0
    private val MAX = 1000

    private lateinit var randomNumberMessenger: Messenger

    inner class RandomNumberRequestHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_RANDOM_NUMBER -> {
                    val messageSendRandomNumber = Message.obtain(null, MSG_RANDOM_NUMBER)
                    messageSendRandomNumber.arg1 = getRandomNumber()
                    try {
                        msg.replyTo.send(messageSendRandomNumber)
                    } catch (e: RemoteException) {
                        Log.d(TAG, e.message.toString())
                    }
                }
                else -> super.handleMessage(msg)
            }
            super.handleMessage(msg)
        }
    }


    override fun onBind(p0: Intent?): IBinder? {
        randomNumberMessenger = Messenger(RandomNumberRequestHandler(this))
        return randomNumberMessenger.binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isRandomNumberGeneratorInUse = true
        Thread(Runnable { generateRandomNumbers() }).start()
        Toast.makeText(applicationContext, "Service has been started", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    fun generateRandomNumbers() {
        while (isRandomNumberGeneratorInUse) {
            try {
                Thread.sleep(1000)
                if (isRandomNumberGeneratorInUse) {
                    randomNumber = Random.nextInt(MIN, MAX)
                    Log.d(TAG, "Random Number is $randomNumber")
                }
            } catch (
                e: InterruptedException
            ) {
                Log.d("RandomNumber", "Interrupted")
            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    fun getRandomNumber() = randomNumber

    override fun onDestroy() {
        super.onDestroy()
        isRandomNumberGeneratorInUse = false
        Toast.makeText(applicationContext, "Service has been stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

}
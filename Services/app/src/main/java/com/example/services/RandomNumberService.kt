package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class RandomNumberService : Service() {
    private val TAG = "RandomNumber"
    private var randomNumber = 0
    private var isRandomNumberGeneratorInUse = false

    private val binder = RandomNumberServiceBinder()

    private val MIN = 0
    private val MAX = 1000

    inner class RandomNumberServiceBinder : Binder() {
        fun getService(): RandomNumberService = this@RandomNumberService
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        return binder
    }

    override fun onRebind(intent: Intent?) {
        Log.d(TAG, "onRebind")
        super.onRebind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("RandomNumber", "onStartCommand")
        isRandomNumberGeneratorInUse = true
        Thread(Runnable { randomNumberGenerator() }).start()
        return START_STICKY
    }

    fun randomNumberGenerator() {
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

    override fun onDestroy() {
        super.onDestroy()
        isRandomNumberGeneratorInUse = false
        Log.d(TAG, "onDestroy")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    fun getRandomNumber(): Int {
        return randomNumber
    }

}
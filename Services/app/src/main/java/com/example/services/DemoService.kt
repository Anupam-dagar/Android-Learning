package com.example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class DemoService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        Log.d("DemoService", "DemoService destroyed")
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("DemoService", "Executing onstartcommand: ${Thread.currentThread().id}")
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }
}
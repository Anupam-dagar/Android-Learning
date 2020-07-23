package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Secondactivity : AppCompatActivity() {
    val LIFECYCLE_ACTIVITY = "Second Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LIFECYCLE_ACTIVITY, "onCreate executed")
        setContentView(R.layout.activity_secondactivity)
    }

    override fun onStart() {
        Log.i(LIFECYCLE_ACTIVITY, "onStart executed")
        super.onStart()
    }

    override fun onResume() {
        Log.i(LIFECYCLE_ACTIVITY, "onResume executed")
        super.onResume()
    }

    override fun onStop() {
        Log.i(LIFECYCLE_ACTIVITY, "onStop executed")
        super.onStop()
    }

    override fun onPause() {
        Log.i(LIFECYCLE_ACTIVITY, "onPause executed")
        super.onPause()
    }

    override fun onRestart() {
        Log.i(LIFECYCLE_ACTIVITY, "onRestart executed")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.i(LIFECYCLE_ACTIVITY, "onDestroy executed")
        super.onDestroy()
    }
}
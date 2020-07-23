package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.util.Log

class MainActivity : AppCompatActivity() {
    val LIFECYCLE_ACTIVITY = "Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(LIFECYCLE_ACTIVITY, "onCreate executed")
        var btn_next = findViewById<Button>(R.id.btn_next)

        btn_next.setOnClickListener {
            var i = Intent(this, Secondactivity::class.java)
            startActivity(i)
        }
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
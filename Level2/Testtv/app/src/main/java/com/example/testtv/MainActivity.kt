package com.example.testtv

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeToSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }
}
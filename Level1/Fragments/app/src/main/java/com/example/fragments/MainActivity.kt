package com.example.fragments

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = ListFragment()
            transaction.replace(R.id.fragment_holder, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
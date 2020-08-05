package com.example.unsplashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val gridFragment = GridFragment()
        transaction.replace(R.id.fragment_holder, gridFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
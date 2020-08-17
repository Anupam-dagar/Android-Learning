package com.example.movieproject.Activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.movieproject.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
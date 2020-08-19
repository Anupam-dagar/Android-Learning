package com.example.movieproject.Activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.base.utils.InjectUtils
import com.example.movieproject.R
import com.example.movieproject.Di.Components.DaggerApplicationComponent
import dagger.android.AndroidInjection


class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }
}
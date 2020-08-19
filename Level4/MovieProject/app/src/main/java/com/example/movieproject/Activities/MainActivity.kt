package com.example.movieproject.Activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.base.di.components.DaggerBaseComponent
import com.example.base.di.modules.BaseModule
import com.example.base.utils.InjectUtils
import com.example.movieproject.Di.Components.DaggerApplicationComponent
import com.example.movieproject.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerApplicationComponent.builder().baseComponent(InjectUtils.provideBaseComponent(applicationContext)).build().inject(this)
    }
}
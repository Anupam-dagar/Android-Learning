package com.example.tvfoodapp

import android.app.Application
import com.example.tvfoodapp.Components.ApplicationComponent
import com.example.tvfoodapp.Components.DaggerApplicationComponent

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent.inject(this)
    }
}
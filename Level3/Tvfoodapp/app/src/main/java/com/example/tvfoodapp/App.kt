package com.example.tvfoodapp

import android.app.Application
import com.example.tvfoodapp.Components.ApplicationComponent
import com.example.tvfoodapp.Components.DaggerApplicationComponent

class App : Application() {
    lateinit var instance: App
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        instance = this
        var ac = DaggerApplicationComponent.create()
        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent.inject(this)
    }

    fun getinstance(): Application = instance

    fun applicationComponent(): ApplicationComponent = applicationComponent
}
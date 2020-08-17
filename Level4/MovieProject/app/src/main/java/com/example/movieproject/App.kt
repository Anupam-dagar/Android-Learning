package com.example.movieproject

import android.app.Application
import com.example.movieproject.Di.Components.ApplicationComponent
import com.example.movieproject.Di.Components.DaggerApplicationComponent

class App: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
        applicationComponent.inject(this)
    }
}
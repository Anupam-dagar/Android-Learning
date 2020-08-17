package com.example.movieproject

import android.app.Application
import com.example.base.Di.Components.BaseComponent
import com.example.base.Di.Components.BaseComponentProvider
import com.example.base.Di.Components.DaggerBaseComponent

class App: Application(), BaseComponentProvider {
    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()
        baseComponent = DaggerBaseComponent.create()
        baseComponent.inject(this)
    }

    override fun provideBaseComponent(): BaseComponent {
        return baseComponent
    }
}
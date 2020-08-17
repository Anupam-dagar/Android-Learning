package com.example.movieproject

import android.app.Application
import com.example.base.di.components.BaseComponent
import com.example.base.di.components.BaseComponentProvider
import com.example.base.di.components.DaggerBaseComponent

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
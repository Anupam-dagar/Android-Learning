package com.example.movieproject

import android.app.Application
import com.example.base.di.components.BaseComponent
import com.example.base.di.components.BaseComponentProvider
import com.example.base.di.components.DaggerBaseComponent
import com.example.base.di.modules.BaseModule
import com.example.base.utils.InjectUtils

class App: Application(), BaseComponentProvider {
    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()
        baseComponent = DaggerBaseComponent.builder().baseModule(BaseModule()).build()
        baseComponent.inject(this)
    }

    override fun provideBaseComponent(): BaseComponent {
        return baseComponent
    }
}
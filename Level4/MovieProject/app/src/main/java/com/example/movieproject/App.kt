package com.example.movieproject

import android.app.Application
import android.content.Context
import com.example.base.di.components.BaseComponent
import com.example.base.di.components.BaseComponentProvider
import com.example.base.di.components.DaggerBaseComponent
import com.example.movieproject.Di.Components.DaggerApplicationComponent
import com.example.utils.di.components.DaggerUtilsComponent
import com.example.base.utils.InjectUtils
import com.example.utils.di.components.UtilsComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    private lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()
//        baseComponent = DaggerBaseComponent.builder().baseModule(InjectUtils.provideBaseComponent(applicationContext)).build()
//        baseComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .baseComponent(provideBaseComponent())
            .utilsComponent(getUtilsComponent())
            .build()
    }

//    override fun provideBaseComponent(): BaseComponent {
//        return baseComponent
//    }

    fun provideBaseComponent(): BaseComponent {
        if(!this::baseComponent.isInitialized) {
            baseComponent = DaggerBaseComponent.builder().build()
        }

        return baseComponent

    }

    private fun getUtilsComponent(): UtilsComponent {
        return DaggerUtilsComponent
            .builder()
            .application(this)
            .build()
    }
}
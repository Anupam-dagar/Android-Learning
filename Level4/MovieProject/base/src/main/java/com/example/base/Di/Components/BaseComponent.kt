package com.example.base.Di.Components

import android.app.Application
import com.example.base.Di.Modules.BaseModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [BaseModule::class])
interface BaseComponent {
    fun inject(app: Application)
}
package com.example.base.di.components

import android.app.Application
import com.example.base.di.modules.BaseModule
import dagger.Component

@Component(modules = [BaseModule::class])
interface BaseComponent {
    fun inject(app: Application)
}
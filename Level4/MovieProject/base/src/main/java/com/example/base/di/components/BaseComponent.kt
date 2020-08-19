package com.example.base.di.components

import android.app.Application
import com.example.base.data.Database
import com.example.base.di.modules.BaseModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {
}
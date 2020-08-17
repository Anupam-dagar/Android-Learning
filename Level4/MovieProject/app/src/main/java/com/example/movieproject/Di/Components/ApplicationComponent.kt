package com.example.movieproject.Di.Components

import com.example.movieproject.App
import com.example.movieproject.Di.Modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: App)
}
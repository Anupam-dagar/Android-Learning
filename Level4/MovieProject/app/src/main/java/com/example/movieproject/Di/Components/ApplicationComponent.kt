package com.example.movieproject.Di.Components

import com.example.base.di.components.BaseComponent
import com.example.movieproject.Activities.MainActivity
import com.example.movieproject.Di.Modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class], dependencies = [BaseComponent::class])
interface ApplicationComponent {
     fun inject(mainActivity: MainActivity)
}
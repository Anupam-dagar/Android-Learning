package com.example.movieproject.Di.Components

import com.example.base.Di.Components.BaseComponent
import com.example.movieproject.Di.Modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class], dependencies = [BaseComponent::class])
interface ApplicationComponent {
}
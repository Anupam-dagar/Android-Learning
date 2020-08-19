package com.example.movieproject.Di.Components

import com.example.base.di.components.BaseComponent
import com.example.movieproject.Activities.MainActivity
import com.example.movieproject.Di.Modules.ApplicationModule
import com.example.movieproject.Di.scopes.Appscope
import dagger.Component
import javax.inject.Singleton

@Appscope
@Component(modules = [ApplicationModule::class], dependencies = [BaseComponent::class])
interface ApplicationComponent {
     fun inject(mainActivity: MainActivity)
}
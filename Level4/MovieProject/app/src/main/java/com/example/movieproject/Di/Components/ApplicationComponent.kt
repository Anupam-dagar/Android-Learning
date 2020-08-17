package com.example.movieproject.Di.Components

import com.example.base.di.components.BaseComponent
import com.example.movieproject.Di.Modules.ApplicationModule
import com.example.ui.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class], dependencies = [BaseComponent::class])
interface ApplicationComponent {
    fun inject(mainFragment: MainFragment)
}
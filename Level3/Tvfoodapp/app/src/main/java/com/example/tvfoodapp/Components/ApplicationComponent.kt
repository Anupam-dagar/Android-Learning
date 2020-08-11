package com.example.tvfoodapp.Components

import com.example.tvfoodapp.App
import com.example.tvfoodapp.Dagger.Modules.ApplicationModule
import com.example.tvfoodapp.ui.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(mainFragment: MainFragment)
}
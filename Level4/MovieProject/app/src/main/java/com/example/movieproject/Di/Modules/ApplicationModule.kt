package com.example.movieproject.Di.Modules

import android.app.Application
import com.example.movieproject.Activities.MainActivity
import com.example.ui.di.modules.UiModule
import com.example.ui.fragments.MainFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [UiModule::class])
    abstract fun mainFragment(): MainFragment
}

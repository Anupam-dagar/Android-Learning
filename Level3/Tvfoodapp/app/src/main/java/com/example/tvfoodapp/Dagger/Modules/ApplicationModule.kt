package com.example.tvfoodapp.Dagger.Modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Singleton
    @Provides
    fun providesApplication(): Application = application
}
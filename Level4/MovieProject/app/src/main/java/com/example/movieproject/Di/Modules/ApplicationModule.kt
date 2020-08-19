package com.example.movieproject.Di.Modules

import android.app.Application
import com.example.movieproject.Di.scopes.Appscope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Appscope
    @Provides
    fun providesApplication(): Application = application
}

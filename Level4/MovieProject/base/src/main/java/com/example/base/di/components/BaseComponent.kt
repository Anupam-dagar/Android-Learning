package com.example.base.di.components

import android.app.Application
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import com.example.base.di.modules.BaseModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {
    fun inject(app: Application)
    fun getDatabase(): Database
    fun getMoviesDao(): MovieDao
}
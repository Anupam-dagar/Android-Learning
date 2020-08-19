package com.example.base.di.modules

import android.app.Application
import androidx.room.Room
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule(val application: Application) {
    @Singleton
    @Provides
    fun providesDatabase(): Database {
        return Room.databaseBuilder(
            application.applicationContext,
            Database::class.java, "database-name"
        ).build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(database: Database): MovieDao = database.movieDao()
}
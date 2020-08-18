package com.example.data.di.modules

import android.app.Application
import androidx.room.Room
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesDatabase(applicationContext: Application): Database {
        return Room.databaseBuilder(
            applicationContext,
            Database::class.java, "database-name"
        ).build()
    }

    @Singleton
    @Provides
    fun providesMovieDao(database: Database): MovieDao = database.movieDao()
}

package com.example.base.di.modules

import android.app.Application
import androidx.room.Room
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import dagger.Module
import dagger.Provides


@Module
class BaseModule(val application: Application) {
    @Provides
    fun providesDatabase(): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java, "database-name"
        ).build()
    }

    @Provides
    fun providesMoviesDao(database: Database): MovieDao = database.moviesDao()
}

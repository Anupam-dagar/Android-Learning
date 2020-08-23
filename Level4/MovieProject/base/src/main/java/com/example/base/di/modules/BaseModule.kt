package com.example.base.di.modules

import android.app.Application
import androidx.room.Room
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import dagger.Module
import dagger.Provides


@Module
open class BaseModule(val application: Application) {
    @Provides
    open fun providesDatabase(): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java, "database-name"
        ).build()
    }

    @Provides
    open fun providesMoviesDao(database: Database): MovieDao = database.moviesDao()
}

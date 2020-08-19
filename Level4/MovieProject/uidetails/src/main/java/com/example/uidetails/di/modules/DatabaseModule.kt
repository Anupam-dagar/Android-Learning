package com.example.uidetails.di.modules

import android.content.Context
import androidx.room.Room
import com.example.uidetails.data.Database
import com.example.uidetails.data.dao.MovieDao
import com.example.uidetails.di.scopes.UiDetailScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(val application: Context) {
    @UiDetailScope
    @Provides
    fun providesDatabase(): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java, "database-name"
        ).build()
    }

    @UiDetailScope
    @Provides
    fun providesMoviesDao(database: Database): MovieDao = database.moviesDao()
}
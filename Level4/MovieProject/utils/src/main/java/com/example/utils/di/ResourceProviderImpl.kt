package com.example.utils.di

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.base.IResourceProvider
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import javax.inject.Singleton

@Singleton
class ResourceProviderImpl(val context: Context) : IResourceProvider {
    @Singleton
    override fun providesDatabase(): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java, "database-name"
        ).build()
    }

    override fun providesMovieDao(database: Database): MovieDao = database.movieDao()
}
package com.example.base

import android.app.Application
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao

interface IResourceProvider {
    fun providesDatabase(): Database
    fun providesMovieDao(database: Database): MovieDao
}
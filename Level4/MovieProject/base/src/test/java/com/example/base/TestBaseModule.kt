package com.example.base

import android.app.Application
import androidx.room.Room
import com.example.base.data.Database
import com.example.base.data.dao.MovieDao
import com.example.base.di.modules.BaseModule
import dagger.Provides
import io.mockk.mockk

class TestBaseModule(val applicationContext: Application): BaseModule(applicationContext) {

    override fun providesDatabase(): Database = mockk()

    override fun providesMoviesDao(database: Database): MovieDao = mockk()
}

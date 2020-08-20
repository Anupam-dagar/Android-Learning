package com.example.base.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base.data.dao.MovieDao
import com.example.base.data.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
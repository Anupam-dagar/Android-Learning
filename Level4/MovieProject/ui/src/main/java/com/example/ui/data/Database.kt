package com.example.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ui.data.dao.MovieDao
import com.example.ui.data.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
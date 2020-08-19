package com.example.uidetails.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uidetails.data.dao.MovieDao
import com.example.uidetails.data.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
package com.example.base.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.base.data.entity.Movie

@Dao
abstract class MovieDao {
    //    fun getMovie(): Movie
    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): List<Movie>

    @Insert
    abstract fun addMovies(moviesData: List<Movie>)
}
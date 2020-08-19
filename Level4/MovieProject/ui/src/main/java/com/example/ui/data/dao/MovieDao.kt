package com.example.ui.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ui.data.entity.Movie

@Dao
interface MovieDao {
//    fun getMovie(): Movie
    @Query("SELECT * FROM Movie")
    fun getMovies(): List<Movie>

    @Insert
    fun addMovies(moviesData: List<Movie>)
}
package com.example.uidetails.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.uidetails.data.entity.Movie
import com.example.uidetails.di.scopes.UiDetailScope

@UiDetailScope
@Dao
abstract class MovieDao {
    //    fun getMovie(): Movie
    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): List<Movie>

    @Insert
    abstract fun addMovies(moviesData: List<Movie>)
}
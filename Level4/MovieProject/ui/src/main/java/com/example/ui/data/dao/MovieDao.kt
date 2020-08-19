package com.example.ui.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ui.data.entity.Movie
import com.example.ui.di.scopes.UiScope

@UiScope
@Dao
abstract class MovieDao {
//    fun getMovie(): Movie
    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): List<Movie>

    @Insert
    abstract fun addMovies(moviesData: List<Movie>)
}
package com.example.base.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.base.data.entity.Movie

@Dao
abstract class MovieDao {
    //    fun getMovie(): Movie
    @Query("SELECT * FROM Movie")
    abstract fun getMovies(): LiveData<List<Movie>>

    @Insert
    abstract fun addMovies(moviesData: List<Movie>)

    @Insert
    abstract fun addMovie(movie: Movie)

    @Delete
    abstract fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE id = :id")
    abstract fun getMovie(id: Int): Movie
}
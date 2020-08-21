package com.example.base.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.base.data.entity.Movie

@Dao
abstract class MovieDao {
    @Query("SELECT * FROM Movie WHERE movieType = :queryVal")
    abstract fun getMovies(queryVal: String): LiveData<List<Movie>>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    abstract fun addMovies(moviesData: List<Movie>)

    @Insert
    abstract fun addMovie(movie: Movie)

    @Delete
    abstract fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE id = :id")
    abstract fun getMovie(id: Int): Movie

    @Update
    abstract fun updateMovie(movie: Movie)
}
package com.example.base.data.repositories

import androidx.lifecycle.LiveData
import com.example.base.data.Database
import com.example.base.data.entity.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(private val database: Database) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies(): LiveData<List<Movie>> = database.moviesDao().getMovies()

    fun addMovies(moviesList: List<Movie>) {
        database.moviesDao().addMovies(moviesList)
    }

    fun addMovie(movie: Movie) = database.moviesDao().addMovie(movie)

    fun deleteMovie(movie: Movie) = database.moviesDao().deleteMovie(movie)

    fun getMovie(id: Int): Movie = database.moviesDao().getMovie(id)
}
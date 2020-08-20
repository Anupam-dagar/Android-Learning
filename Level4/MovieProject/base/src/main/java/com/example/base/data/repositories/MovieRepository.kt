package com.example.base.data.repositories

import com.example.base.data.Database
import com.example.base.data.entity.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(private val database: Database) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies(): List<Movie> = database.moviesDao().getMovies()

    fun addMovies(moviesList: List<Movie>) {
        database.moviesDao().addMovies(moviesList)
    }
}
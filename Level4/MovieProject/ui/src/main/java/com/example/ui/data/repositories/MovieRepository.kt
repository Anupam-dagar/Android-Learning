package com.example.ui.data.repositories

import com.example.ui.data.Database
import com.example.ui.data.entity.Movie
import com.example.ui.di.scopes.UiScope
import javax.inject.Inject

@UiScope
class MovieRepository @Inject constructor(private val database: Database) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies(): List<Movie> = database.moviesDao().getMovies()

    fun addMovies(moviesList: List<Movie>) {
        database.moviesDao().addMovies(moviesList)
    }
}
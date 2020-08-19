package com.example.uidetails.data.repositories

import com.example.uidetails.data.Database
import com.example.uidetails.data.entity.Movie
import com.example.uidetails.di.scopes.UiDetailScope
import javax.inject.Inject

@UiDetailScope
class MovieRepository @Inject constructor(private val database: Database) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies(): List<Movie> = database.moviesDao().getMovies()

    fun addMovies(moviesList: List<Movie>) {
        database.moviesDao().addMovies(moviesList)
    }
}
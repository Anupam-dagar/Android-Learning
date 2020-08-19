package com.example.ui.data.repositories

import com.example.ui.data.dao.MovieDao
import com.example.ui.data.entity.Movie
import com.example.ui.di.scopes.UiScope
import javax.inject.Inject
import javax.inject.Singleton

@UiScope
class MovieRepository @Inject constructor(private val movieDao: MovieDao) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies() = movieDao.getMovies()

    fun addMovies(moviesList: List<Movie>) {
        movieDao.addMovies(moviesList)
    }
}
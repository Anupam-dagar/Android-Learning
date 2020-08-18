package com.example.base.data.repositories

import com.example.base.data.dao.MovieDao
import com.example.base.data.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao) {
    fun getMovie() {
        TODO("NOT YET IMPLEMENTED")
    }

    fun getMovies() = movieDao.getMovies()

    fun addMovies(moviesList: List<Movie>) {
        movieDao.addMovies(moviesList)
    }
}
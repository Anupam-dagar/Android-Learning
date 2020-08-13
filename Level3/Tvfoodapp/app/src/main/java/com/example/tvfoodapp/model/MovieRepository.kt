package com.example.tvfoodapp.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDAO: MovieDAO) {
    fun addMovie(movie: Movie) {
        movieDAO.addMovie(movie)
    }

    fun getMovies() = movieDAO.getMovies()

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(movieDAO: MovieDAO) = instance ?: synchronized(this) {
            instance ?: MovieRepository(movieDAO).also { instance = it }
        }
    }
}

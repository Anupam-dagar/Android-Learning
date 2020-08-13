package com.example.tvfoodapp.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDAO: MovieDAO) {
    fun addMovie(movie: Movie) {
        movieDAO.addMovie(movie)
    }

    fun getMovies() = movieDAO.getMovies()

    fun addMovies(moviesList: List<Movie>) {
        movieDAO.addMovies(moviesList)
    }

    fun addTopRatedMovie(movie: Movie) {
        movieDAO.addTopRatedMovie(movie)
    }

    fun getTopRatedMovies(): List<Movie> = movieDAO.getTopRatedMovies()

    fun addTopRatedMovies(moviesList: List<Movie>) {
        movieDAO.addTopRatedMovies(moviesList)
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(movieDAO: MovieDAO) = instance ?: synchronized(this) {
            instance ?: MovieRepository(movieDAO).also { instance = it }
        }
    }
}

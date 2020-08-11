package com.example.tvfoodapp.model

class MovieRepository private constructor(private val movieDAO: MovieDAO) {
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

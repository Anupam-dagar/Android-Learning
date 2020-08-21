package com.example.base.data.repositories

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.base.data.Database
import com.example.base.data.entity.FavouriteMovie
import com.example.base.data.entity.Movie
import javax.inject.Inject

class MovieRepository @Inject constructor(private val database: Database) {
    fun getMovies(queryVal: String): LiveData<List<Movie>> = database.moviesDao().getMovies(queryVal)

    fun addMovies(moviesList: List<Movie>) {
        database.moviesDao().addMovies(moviesList)
    }

    fun addMovie(movie: Movie) = database.moviesDao().addMovie(movie)

    fun deleteMovie(movie: Movie) = database.moviesDao().deleteMovie(movie)

    fun getMovie(id: Int): Movie = database.moviesDao().getMovie(id)

    fun updateMovie(movie: Movie) = database.moviesDao().updateMovie(movie)

    fun addFavouriteMovies(moviesData: List<FavouriteMovie>) = database.favouriteMovieDao().addFavouriteMovies(moviesData)

    fun addFavouriteMovie(movie: FavouriteMovie) = database.favouriteMovieDao().addFavouriteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMovie) = database.favouriteMovieDao().deleteFavouriteMovie(movie)

    fun getFavouriteMovie(id: Int): FavouriteMovie = database.favouriteMovieDao().getFavouriteMovie(id)

    fun getFavouriteMovies(): LiveData<List<FavouriteMovie>> = database.favouriteMovieDao().getFavouriteMovies()

    fun updateFavouriteMovie(movie: FavouriteMovie) = database.favouriteMovieDao().updateFavouriteMovie(movie)
}
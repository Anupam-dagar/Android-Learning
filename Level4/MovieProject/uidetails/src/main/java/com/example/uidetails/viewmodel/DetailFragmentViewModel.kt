package com.example.uidetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base.data.entity.FavouriteMovie
import com.example.base.data.entity.Movie
import com.example.base.data.repositories.MovieRepository
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(queryVal: String): LiveData<List<Movie>> = movieRepository.getMovies(queryVal)

    fun addMovies(moviesList: List<Movie>) = movieRepository.addMovies(moviesList)

    fun addMovie(movie: Movie) = movieRepository.addMovie(movie)

    fun deleteMovie(movie: Movie) = movieRepository.deleteMovie(movie)

    fun getMovie(id: Int): Movie = movieRepository.getMovie(id)

    fun updateMovie(movie: Movie) = movieRepository.updateMovie(movie)

    fun addFavouriteMovies(moviesData: List<FavouriteMovie>) = movieRepository.addFavouriteMovies(moviesData)

    fun addFavouriteMovie(movie: FavouriteMovie) = movieRepository.addFavouriteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMovie) = movieRepository.deleteFavouriteMovie(movie)

    fun getFavouriteMovie(id: Int): FavouriteMovie = movieRepository.getFavouriteMovie(id)

    fun getFavouriteMovies(): LiveData<List<FavouriteMovie>> = movieRepository.getFavouriteMovies()

    fun updateFavouriteMovie(movie: FavouriteMovie) = movieRepository.updateFavouriteMovie(movie)
}

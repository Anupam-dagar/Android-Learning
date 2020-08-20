package com.example.uidetails.viewmodel

import androidx.lifecycle.ViewModel
import com.example.base.data.entity.Movie
import com.example.base.data.repositories.MovieRepository
import javax.inject.Inject

class DetailFragmentViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): List<Movie> = movieRepository.getMovies()

    fun addMovies(moviesList: List<Movie>) = movieRepository.addMovies(moviesList)

    fun addMovie(movie: Movie) = movieRepository.addMovie(movie)

    fun deleteMovie(movie: Movie) = movieRepository.deleteMovie(movie)
}

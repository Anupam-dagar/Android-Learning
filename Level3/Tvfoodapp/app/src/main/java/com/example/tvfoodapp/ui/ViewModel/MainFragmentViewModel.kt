package com.example.tvfoodapp.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.tvfoodapp.model.Movie
import com.example.tvfoodapp.model.MovieRepository
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies() = movieRepository.getMovies()

    fun addMovie(movie: Movie) = movieRepository.addMovie(movie)

    fun addMovies(moviesList: List<Movie>) = movieRepository.addMovies(moviesList)

    fun addTopRatedMovie(movie: Movie) {
        movieRepository.addTopRatedMovie(movie)
    }

    fun getTopRatedMovies(): List<Movie> = movieRepository.getTopRatedMovies()

    fun addTopRatedMovies(moviesList: List<Movie>) {
        movieRepository.addTopRatedMovies(moviesList)
    }
}
package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.base.data.entity.Movie
import com.example.base.data.repositories.MovieRepository
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<List<Movie>> = movieRepository.getMovies()

    fun addMovies(moviesList: List<Movie>) = movieRepository.addMovies(moviesList)

    fun addMovie(movie: Movie) = movieRepository.addMovie(movie)

    fun deleteMovie(movie: Movie) = movieRepository.deleteMovie(movie)

    fun getMovie(id: Int): Movie = movieRepository.getMovie(id)
}

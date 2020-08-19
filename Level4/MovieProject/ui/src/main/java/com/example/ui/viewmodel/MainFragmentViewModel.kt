package com.example.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ui.data.entity.Movie
import com.example.ui.data.repositories.MovieRepository
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): List<Movie> = movieRepository.getMovies()

    fun addMovies(moviesList: List<Movie>) = movieRepository.addMovies(moviesList)
}

package com.example.tvfoodapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MovieDAO {
    private val movieList = mutableListOf<Movie>()
    private val movies = MutableLiveData<List<Movie>>()

    init {
        movies.value = movieList
    }

    fun addMovie(movie: Movie) {
        movieList.add(movie)
        movies.value = movieList
    }

    fun getMovies(): LiveData<List<Movie>> = movies
}

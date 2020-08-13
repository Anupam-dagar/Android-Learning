package com.example.tvfoodapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDAO @Inject constructor(){
    private val popularMovieList = mutableListOf<Movie>()
    private val popularMovies = MutableLiveData<List<Movie>>()
    private val topRatedMovieList = mutableListOf<Movie>()
    private val topRatedMovies = MutableLiveData<List<Movie>>()

    init {
        popularMovies.value = popularMovieList
        topRatedMovies.value = topRatedMovieList
    }

    fun addMovie(movie: Movie) {
        popularMovieList.add(movie)
        popularMovies.value = popularMovieList
    }

    fun getMovies(): List<Movie> = popularMovieList

    fun addMovies(moviesList: List<Movie>) {
        popularMovieList.addAll(moviesList)
        popularMovies.value = popularMovieList
    }

    fun addTopRatedMovie(movie: Movie) {
        topRatedMovieList.add(movie)
    }

    fun getTopRatedMovies(): List<Movie> = topRatedMovieList

    fun addTopRatedMovies(moviesList: List<Movie>) {
        topRatedMovieList.addAll(moviesList)
        topRatedMovies.value = topRatedMovieList
    }
}

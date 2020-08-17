package com.example.data.dao

import androidx.lifecycle.MutableLiveData
import com.example.data.entity.Movie
import javax.inject.Singleton

@Singleton
class FavouriteMoviesDao: MovieDao {
    private val moviesList = mutableListOf<Movie>()
    private val movies = MutableLiveData<List<Movie>>()

    override fun getMovie(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovies(): List<Movie> = moviesList

    override fun addMovies(moviesData: List<Movie>) {
        moviesList.addAll(moviesData)
        movies.value = moviesList
    }
}
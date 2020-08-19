package com.example.ui.data.dao

import androidx.lifecycle.MutableLiveData
import com.example.ui.data.entity.Movie
import com.example.ui.di.scopes.UiScope
import javax.inject.Singleton

@UiScope
class FavouriteMoviesDao: MovieDao {
    private val moviesList = mutableListOf<Movie>()
    private val movies = MutableLiveData<List<Movie>>()

//    override fun getMovie(): Movie {
//        TODO("Not yet implemented")
//    }

    override fun getMovies(): List<Movie> = moviesList

    override fun addMovies(moviesData: List<Movie>) {
        moviesList.addAll(moviesData)
        movies.value = moviesList
    }
}
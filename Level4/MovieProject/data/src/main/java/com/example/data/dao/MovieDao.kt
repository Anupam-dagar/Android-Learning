package com.example.data.dao

import com.example.data.entity.Movie

interface MovieDao {
    fun getMovie(): Movie
    fun getMovies(): List<Movie>
    fun addMovies(moviesData: List<Movie>)
}
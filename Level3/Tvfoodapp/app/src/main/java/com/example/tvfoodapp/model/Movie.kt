package com.example.tvfoodapp.model

data class Movie(
    val popularity: Double,
    val vote_count: Int,
    val poster_path: String,
    val id: Int,
    val backdrop_path: String,
    val title: String,
    val vote_average: Double,
    val overview: String,
    val release_date: String
)

data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)
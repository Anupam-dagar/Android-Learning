package com.example.data.entity

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

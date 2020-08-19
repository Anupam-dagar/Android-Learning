package com.example.ui.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @ColumnInfo(name="vote_count") val vote_count: Int,
    @ColumnInfo(name="popularity") val popularity: Double,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "vote_average") val vote_average: Double,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val release_date: String
)

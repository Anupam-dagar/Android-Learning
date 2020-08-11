package com.example.tvfoodapp.model.API

import com.example.tvfoodapp.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<Movie>
}
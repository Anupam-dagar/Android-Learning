package com.example.tvfoodapp.model.API

import com.example.tvfoodapp.model.Movie
import com.example.tvfoodapp.model.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>
}
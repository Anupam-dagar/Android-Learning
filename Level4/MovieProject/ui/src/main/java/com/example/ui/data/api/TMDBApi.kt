package com.example.ui.data.api

import com.example.ui.data.entity.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>
}

package com.example.unsplashapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApiInterface {

    @GET("curated")
    fun getImages(@Query("per_page") numImages: Int): Call<PexelsDataResponse>
}
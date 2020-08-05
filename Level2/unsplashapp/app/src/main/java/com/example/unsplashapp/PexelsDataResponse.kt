package com.example.unsplashapp

data class PexelsDataResponseSrc(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)

data class PexelsDataPhoto(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val photographer_id: Int,
    val src: PexelsDataResponseSrc
)

data class PexelsDataResponse(
    val page: Int,
    val per_page: Int,
    val photos: Collection<PexelsDataPhoto>
)
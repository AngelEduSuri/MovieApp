package com.aesuriagasalazar.movieapp.domain

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String,
    val budget: Long,
    val genre: List<Genre>,
    val originalLanguage: String,
    val posterPath: String,
    val release: String,
    val duration: Int,
    val average: Double,
    val cast: List<Cast>,
    val tagline: String,
    val status: String,
    val revenue: Long,
    val companies: List<Company>
)


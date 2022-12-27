package com.aesuriagasalazar.movieapp.domain

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
    val release: String,
    val average: Double,
    val genres: List<String>,
)

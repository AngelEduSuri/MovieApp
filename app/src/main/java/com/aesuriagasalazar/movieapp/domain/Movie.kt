package com.aesuriagasalazar.movieapp.domain

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val average: Double,
    val genres: List<Genre>,
)

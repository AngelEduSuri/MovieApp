package com.aesuriagasalazar.movieapp.framework.data.domain

import com.aesuriagasalazar.movieapp.domain.Genre

data class RemoteGenre(
    val id: Int,
    val name: String
)

data class RemoteListGenreResult(
    val genres: List<RemoteGenre>
)

fun List<RemoteGenre>.toDomainGenres(): List<Genre> = map {
    Genre(it.id, it.name)
}
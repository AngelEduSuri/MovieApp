package com.aesuriagasalazar.movieapp.framework.data.domain

data class RemoteGenre(
    val id: Int,
    val name: String
)

data class RemoteListGenreResult(
    val genres: List<RemoteGenre>
)
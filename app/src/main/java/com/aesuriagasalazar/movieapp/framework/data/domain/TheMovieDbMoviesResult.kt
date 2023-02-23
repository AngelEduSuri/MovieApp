package com.aesuriagasalazar.movieapp.framework.data.domain

import com.aesuriagasalazar.movieapp.domain.Movie
import com.google.gson.annotations.SerializedName

data class RemoteMovie(
    val id: Int,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("poster_path") val posterPath: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
)

data class RemoteListPopularMoviesResult(
    val page: Int,
    val results: List<RemoteMovie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_result") val totalResults: Int
)

fun List<RemoteMovie>.toDomainMovies(genreList: List<RemoteGenre>): List<Movie> = map { movie ->
    Movie(
        id = movie.id,
        title = movie.title,
        poster = "https://image.tmdb.org/t/p/w500".plus(movie.posterPath),
        average = movie.voteAverage,
        genres = genreList.toDomainGenres().filter {
            movie.genreIds.contains(it.id)
        }
    )
}
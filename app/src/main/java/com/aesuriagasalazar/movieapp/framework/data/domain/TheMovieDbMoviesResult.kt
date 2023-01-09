package com.aesuriagasalazar.movieapp.framework.data.domain

import com.aesuriagasalazar.movieapp.domain.Movie
import com.google.gson.annotations.SerializedName

data class RemoteMovie(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
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
        overview = movie.overview,
        poster = "https://image.tmdb.org/t/p/w500/".plus(movie.posterPath),
        release = movie.releaseDate,
        average = movie.voteAverage,
        genres = genreList.toDomainGenres().filter {
            movie.genreIds.contains(it.id)
        }
    )
}
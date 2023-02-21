package com.aesuriagasalazar.movieapp.framework.data.domain

import com.aesuriagasalazar.movieapp.domain.Cast
import com.aesuriagasalazar.movieapp.domain.Company
import com.aesuriagasalazar.movieapp.domain.MovieDetails
import com.google.gson.annotations.SerializedName

data class RemoteMovieDetails(
    @SerializedName("backdrop_path") val backdropPath: String,
    val budget: Long,
    val genres: List<RemoteGenre>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Long,
    val status: String,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    val credits: Credits,
    val runtime: Int
)

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class Credits(
    val cast: List<RemoteCast>
)

data class RemoteCast(
    val id: Int,
    val gender: Int,
    val name: String,
    val character: String,
    @SerializedName("profile_path") val profilePath: String,
    val order: Int
)

fun RemoteMovieDetails.toDomainMovieDetails(): MovieDetails = MovieDetails(
    id = this.id,
    title = this.title,
    overview = this.overview,
    backdropPath = "https://image.tmdb.org/t/p/w780".plus(this.backdropPath),
    budget = this.budget,
    genre = this.genres.toDomainGenres(),
    originalLanguage = this.originalLanguage,
    posterPath = "https://image.tmdb.org/t/p/w500".plus(this.posterPath),
    release = this.releaseDate,
    duration = this.runtime,
    average = this.voteAverage,
    cast = this.credits.cast.toDomainCast(),
    tagline = if (this.tagline.isEmpty()) tagline else "\"${this.tagline}\"",
    status = this.status,
    revenue = this.revenue,
    companies = this.productionCompanies.toDomainCompany()
)

fun List<RemoteCast>.toDomainCast() = map {
    Cast(
        id = it.id,
        name = it.name,
        character = it.character,
        profile = "https://image.tmdb.org/t/p/w342".plus(it.profilePath),
        gender = it.gender
    )
}

fun List<ProductionCompany>.toDomainCompany() = map {
    Company(
        id = it.id,
        logo = "https://image.tmdb.org/t/p/w300".plus(it.logoPath),
        name = it.name,
        country = it.originCountry
    )
}
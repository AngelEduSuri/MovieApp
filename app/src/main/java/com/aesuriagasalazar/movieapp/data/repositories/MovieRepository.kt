package com.aesuriagasalazar.movieapp.data.repositories

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie

class MovieRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun loadMovieGenres(apiKey: String): List<Genre> = remoteDataSource.getAllGenres(apiKey)

    fun loadPopularMovies(): List<Movie> = remoteDataSource.getPopularMovies()
}
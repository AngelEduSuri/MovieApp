package com.aesuriagasalazar.movieapp.data.datasources

import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie

interface RemoteDataSource {
    suspend fun getAllGenres(apiKey: String): List<Genre>
    fun getPopularMovies(): List<Movie>
}
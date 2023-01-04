package com.aesuriagasalazar.movieapp.data.datasources

import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.ResultMovieData

interface RemoteDataSource {
    suspend fun getAllGenres(apiKey: String): ResultMovieData<List<Genre>>
    suspend fun getPopularMovies(apiKey: String): ResultMovieData<List<Movie>>
    suspend fun getMoviesFromGenreId(apiKey: String, genreId: Int): ResultMovieData<List<Movie>>
}
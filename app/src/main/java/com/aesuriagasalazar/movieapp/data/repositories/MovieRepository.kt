package com.aesuriagasalazar.movieapp.data.repositories

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.MovieDetails
import com.aesuriagasalazar.movieapp.domain.ResultMovieData

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val apiKey: String) {

    suspend fun loadMovieGenres(): ResultMovieData<List<Genre>> =
        remoteDataSource.getAllGenres(apiKey)

    suspend fun loadMovies(genreId: Int): ResultMovieData<List<Movie>> = if (genreId == 0) {
        remoteDataSource.getPopularMovies(apiKey)
    } else {
        remoteDataSource.getMoviesFromGenreId(apiKey, genreId)
    }

    suspend fun loadMovieDetails(movieId: Int): ResultMovieData<MovieDetails> = remoteDataSource.getMovieDetails(apiKey, movieId)
}
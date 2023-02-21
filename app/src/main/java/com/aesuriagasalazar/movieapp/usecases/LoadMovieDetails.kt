package com.aesuriagasalazar.movieapp.usecases

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.MovieDetails
import com.aesuriagasalazar.movieapp.domain.ResultMovieData

class LoadMovieDetails(private val movieRepository: MovieRepository) {

    suspend fun invoke(movieId: Int): ResultMovieData<MovieDetails> =
        movieRepository.loadMovieDetails(movieId)
}
package com.aesuriagasalazar.movieapp.usecases

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LoadPopularMovies(private val repository: MovieRepository) {

    suspend fun invoke(): List<Movie> = withContext(Dispatchers.IO) {
        delay(2000)
        repository.loadPopularMovies()
    }
}
package com.aesuriagasalazar.movieapp.usecases

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.domain.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.nio.channels.spi.AbstractSelectionKey

class LoadMovieGenres(private val repository: MovieRepository) {

    suspend fun invoke(apiKey: String): List<Genre> = withContext(Dispatchers.IO) {
        delay(2000)
        repository.loadMovieGenres(apiKey)
    }
}
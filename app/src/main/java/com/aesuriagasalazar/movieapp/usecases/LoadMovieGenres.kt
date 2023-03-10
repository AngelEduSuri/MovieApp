package com.aesuriagasalazar.movieapp.usecases

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadMovieGenres(private val repository: MovieRepository) {

    suspend fun invoke(): ResultMovieData<List<Genre>> = withContext(Dispatchers.IO) {
        repository.loadMovieGenres()
    }
}
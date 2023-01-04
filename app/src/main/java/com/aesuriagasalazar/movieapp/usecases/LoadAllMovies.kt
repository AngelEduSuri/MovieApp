package com.aesuriagasalazar.movieapp.usecases

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadAllMovies(private val repository: MovieRepository) {

    suspend fun invoke(genreId: Int): ResultMovieData<List<Movie>> = withContext(Dispatchers.IO) {
        repository.loadMovies(genreId)
    }
}
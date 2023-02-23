package com.aesuriagasalazar.movieapp.framework.data.sourceimpl

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.MovieDetails
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import com.aesuriagasalazar.movieapp.framework.data.MovieApiService
import com.aesuriagasalazar.movieapp.framework.data.domain.toDomainGenres
import com.aesuriagasalazar.movieapp.framework.data.domain.toDomainMovieDetails
import com.aesuriagasalazar.movieapp.framework.data.domain.toDomainMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ServerMovieDataSourceImpl(private val movieApiService: MovieApiService) : RemoteDataSource {

    override suspend fun getAllGenres(apiKey: String): ResultMovieData<List<Genre>> {
        return try {
            val moviesGenre = movieApiService.getMoviesGenre(apiKey).genres.toDomainGenres()
            ResultMovieData.Success(moviesGenre)
        } catch (e: Exception) {
            ResultMovieData.Error(e.message.plus(": Check your internet connection"))
        }
    }

    override suspend fun getPopularMovies(apiKey: String): ResultMovieData<List<Movie>> {
        return try {
            ResultMovieData.Success(getMovies(apiKey, null))
        } catch (e: Exception) {
            ResultMovieData.Error(e.message.plus(": Check your internet connection"))
        }
    }

    override suspend fun getMoviesFromGenreId(
        apiKey: String,
        genreId: Int
    ): ResultMovieData<List<Movie>> {
        return try {
            ResultMovieData.Success(getMovies(apiKey, genreId))
        } catch (e: Exception) {
            ResultMovieData.Error(e.message.plus(": Check your internet connection"))
        }
    }

    override suspend fun getMovieDetails(apiKey: String, movieId: Int): ResultMovieData<MovieDetails> {
        return try {
            val movie = movieApiService.getMovieDetails(movieId, apiKey).toDomainMovieDetails()
            ResultMovieData.Success(movie)
        } catch (e: Exception) {
            ResultMovieData.Error(e.message)
        }
    }

    private suspend fun getMovies(
        apiKey: String,
        genreId: Int?
    ): List<Movie> {
        val genres = withContext(Dispatchers.IO) {
            async { movieApiService.getMoviesGenre(apiKey) }
        }
        val movies = withContext(Dispatchers.IO) {
            async {
                return@async genreId?.let { movieApiService.getMoviesForGenre(apiKey, it) }
                    ?: movieApiService.getPopularMovies(apiKey)
            }
        }
        return movies.await().results.toDomainMovies(genres.await().genres)
    }
}

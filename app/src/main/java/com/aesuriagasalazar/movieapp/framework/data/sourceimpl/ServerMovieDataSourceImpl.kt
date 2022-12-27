package com.aesuriagasalazar.movieapp.framework.data.sourceimpl

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.framework.data.MovieApiService

class ServerMovieDataSourceImpl(private val movieApiService: MovieApiService) : RemoteDataSource {

    override suspend fun getAllGenres(apiKey: String): List<Genre> {
        val moviesGenre = movieApiService.getMoviesGenre(apiKey)
        return moviesGenre.genres.map {
            Genre(it.id, name = it.name)
        }
    }

    override fun getPopularMovies(): List<Movie> {
        return (1..10).map {
            Movie(
                id = it.toLong(),
                title = "Movie #$it",
                overview = "This is a movie description number #$it",
                poster = "https://picsum.photos/800/1200?random=$it",
                release = "2020-10-24",
                average = it.toDouble(),
                genres = listOf("Accion", "Drama", "Suspenso")
            )
        }
    }
}
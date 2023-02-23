package com.aesuriagasalazar.movieapp.framework.data

import com.aesuriagasalazar.movieapp.framework.data.domain.RemoteListGenreResult
import com.aesuriagasalazar.movieapp.framework.data.domain.RemoteListPopularMoviesResult
import com.aesuriagasalazar.movieapp.framework.data.domain.RemoteMovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list?language=es")
    suspend fun getMoviesGenre(@Query("api_key") apiKey: String): RemoteListGenreResult

    @GET("movie/popular?language=es&page=1")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): RemoteListPopularMoviesResult

    @GET("discover/movie?language=es&sort_by=popularity.desc&page=1")
    suspend fun getMoviesForGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int
    ): RemoteListPopularMoviesResult

    @GET("movie/{movie_id}?language=es&append_to_response=credits")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): RemoteMovieDetails
}
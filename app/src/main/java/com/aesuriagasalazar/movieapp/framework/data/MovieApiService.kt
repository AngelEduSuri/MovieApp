package com.aesuriagasalazar.movieapp.framework.data

import com.aesuriagasalazar.movieapp.framework.data.domain.RemoteListGenreResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list?language=es")
    suspend fun getMoviesGenre(@Query("api_key") apiKey: String): RemoteListGenreResult
}
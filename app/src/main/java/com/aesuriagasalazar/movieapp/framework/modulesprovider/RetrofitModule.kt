package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.framework.data.MovieApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
    retrofit.create(MovieApiService::class.java)

val retrofitModule = module {
    single { BASE_URL }
    single { provideRetrofit(get()) }
    single { provideMovieApiService(get()) }
}
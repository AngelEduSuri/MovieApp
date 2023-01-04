package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.framework.data.MovieApiService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "f44401283b5e759be169d1590da8bf8a"

fun provideBaseUrl(): String = BASE_URL
fun provideApiKey(): String = API_KEY

fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(baseUrl)
    .build()

fun provideMovieApiService(retrofit: Retrofit): MovieApiService =
    retrofit.create(MovieApiService::class.java)

val retrofitModule = module {
    single(named("api_key")) { provideApiKey() }
    single(named("base_url")) { provideBaseUrl() }
    single { provideRetrofit(get(named("base_url"))) }
    single { provideMovieApiService(get()) }
}
package com.aesuriagasalazar.movieapp.domain

sealed class ResultMovieData<out T> {
    object Loading: ResultMovieData<Nothing>()
    data class Success<T>(val data: T): ResultMovieData<T>()
    data class Error(val error: String?): ResultMovieData<Nothing>()
}

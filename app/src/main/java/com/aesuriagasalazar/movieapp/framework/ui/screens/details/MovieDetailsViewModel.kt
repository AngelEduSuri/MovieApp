package com.aesuriagasalazar.movieapp.framework.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.movieapp.domain.MovieDetails
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import com.aesuriagasalazar.movieapp.framework.ui.navigation.NavRoutes
import com.aesuriagasalazar.movieapp.usecases.LoadMovieDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val loadMovieDetails: LoadMovieDetails
) : ViewModel() {

    private val movieId: Int = checkNotNull(savedStateHandle[NavRoutes.DetailScreen.arg])
    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() = viewModelScope.launch {
        _uiState.update {
            it.copy(movieDataResult = loadMovieDetails.invoke(movieId))
        }
    }

    fun updateScrollPosition(newScrollIndex: Int) {
        if (newScrollIndex == uiState.value.appBarIndex) return
        _uiState.update {
            it.copy(appBarScroll = newScrollIndex > uiState.value.appBarIndex, appBarIndex = newScrollIndex)
        }
    }
}

data class MovieDetailsUiState(
    val movieDataResult: ResultMovieData<MovieDetails> = ResultMovieData.Loading,
    val appBarScroll: Boolean = false,
    val appBarIndex: Int = 0
)


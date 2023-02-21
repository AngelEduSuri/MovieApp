package com.aesuriagasalazar.movieapp.framework.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import com.aesuriagasalazar.movieapp.usecases.LoadAllMovies
import com.aesuriagasalazar.movieapp.usecases.LoadMovieGenres
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val loadAllMovies: LoadAllMovies,
    private val loadMovieGenres: LoadMovieGenres
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainViewModelUiState())
    val uiState: StateFlow<MainViewModelUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getMovieGenres()
            getAllMovies()
        }
    }

    private suspend fun getMovieGenres() = viewModelScope.launch {
        _uiState.update { it.copy(dataResponseGenres = loadMovieGenres.invoke()) }
    }

    private suspend fun getAllMovies() = viewModelScope.launch {
        _uiState.update {
            it.copy(dataResponsePopularMovies = loadAllMovies.invoke(uiState.value.currentGenre.id))
        }
    }

    fun onGenreSelected(genre: Genre) {
        viewModelScope.launch {
            val add = loadAllMovies.invoke(genre.id)
            _uiState.update { it.copy(currentGenre = genre, dataResponsePopularMovies = add) }
        }
    }

    fun onGridViewClicked() {
        _uiState.update { it.copy(isGridView = !uiState.value.isGridView) }
    }
}

data class MainViewModelUiState(
    var currentGenre: Genre = Genre(0, "Trending Movies"),
    val dataResponseGenres: ResultMovieData<List<Genre>> = ResultMovieData.Loading,
    val dataResponsePopularMovies: ResultMovieData<List<Movie>> = ResultMovieData.Loading,
    val isGridView: Boolean = true
)
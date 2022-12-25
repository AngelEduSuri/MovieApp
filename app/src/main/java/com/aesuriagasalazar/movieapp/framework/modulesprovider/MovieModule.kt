package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.framework.datasourcesimpl.ServerMovieDataSourceImpl
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainViewModel
import com.aesuriagasalazar.movieapp.usecases.LoadPopularMovies
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single { MovieRepository(ServerMovieDataSourceImpl()) }
    single { LoadPopularMovies(get()) }
    viewModel { MainViewModel(get()) }
}
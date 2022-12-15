package com.aesuriagasalazar.movieapp.data.service.modules

import com.aesuriagasalazar.movieapp.data.repository.MovieRepository
import com.aesuriagasalazar.movieapp.ui.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single { MovieRepository() }
    viewModel { MainViewModel(get())}
}
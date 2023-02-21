package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.framework.ui.screens.details.MovieDetailsViewModel
import com.aesuriagasalazar.movieapp.usecases.LoadMovieDetails
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadMovieDetailsModule = module {
    factory { LoadMovieDetails(get()) }
}

val movieDetailsViewModelModule = module {
    viewModel { MovieDetailsViewModel(get(), get()) }
}

val parentMovieDetailsModule = module {
    includes(
        movieDetailsViewModelModule,
        loadMovieDetailsModule
    )
}
package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.framework.data.sourceimpl.ServerMovieDataSourceImpl
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainViewModel
import com.aesuriagasalazar.movieapp.usecases.LoadMovieGenres
import com.aesuriagasalazar.movieapp.usecases.LoadPopularMovies
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val remoteDataSource = module {
    single<RemoteDataSource> { ServerMovieDataSourceImpl(get()) }
}

val repositoryModule = module {
    single { MovieRepository(get()) }
}

val loadPopularMoviesModule = module {
    single { LoadPopularMovies(get()) }
}

val loadMovieGenresModule = module {
    single { LoadMovieGenres(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val parentPopularMoviesModule = module {
    includes(
        viewModelModule,
        loadPopularMoviesModule,
        loadMovieGenresModule,
        repositoryModule,
        remoteDataSource
    )
}
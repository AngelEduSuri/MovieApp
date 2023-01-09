package com.aesuriagasalazar.movieapp.framework.modulesprovider

import com.aesuriagasalazar.movieapp.data.datasources.RemoteDataSource
import com.aesuriagasalazar.movieapp.data.repositories.MovieRepository
import com.aesuriagasalazar.movieapp.framework.data.sourceimpl.ServerMovieDataSourceImpl
import com.aesuriagasalazar.movieapp.framework.ui.screens.main.MainViewModel
import com.aesuriagasalazar.movieapp.usecases.LoadMovieGenres
import com.aesuriagasalazar.movieapp.usecases.LoadAllMovies
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteDataSource = module {
    single<RemoteDataSource> { ServerMovieDataSourceImpl(get()) }
}

val repositoryModule = module {
    single { MovieRepository(get(), get(named("api_key"))) }
}

val loadAllMoviesModule = module {
    factory { LoadAllMovies(get()) }
}

val loadMovieGenresModule = module {
    factory { LoadMovieGenres(get()) }
}

val movieViewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val parentMoviesModule = module {
    includes(
        movieViewModelModule,
        loadAllMoviesModule,
        loadMovieGenresModule,
        repositoryModule,
        remoteDataSource
    )
}
package com.aesuriagasalazar.movieapp.framework

import android.app.Application
import com.aesuriagasalazar.movieapp.framework.modulesprovider.parentPopularMoviesModule
import com.aesuriagasalazar.movieapp.framework.modulesprovider.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(parentPopularMoviesModule, retrofitModule)
        }
    }
}
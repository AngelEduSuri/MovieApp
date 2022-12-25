package com.aesuriagasalazar.movieapp.framework.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import com.aesuriagasalazar.movieapp.framework.ui.navigation.MovieAppNavigation
import com.aesuriagasalazar.movieapp.framework.ui.theme.MovieAppTheme
import com.aesuriagasalazar.movieapp.framework.ui.theme.PrimaryColorVariantDark
import com.aesuriagasalazar.movieapp.framework.ui.theme.PrimaryColorVariantLight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Remember a SystemUiController
            val systemUiController = rememberSystemUiController()
            val useDarkColor = isSystemInDarkTheme()

            DisposableEffect(systemUiController, useDarkColor) {
                // Update all of the system bar colors to be transparent, and use
                // dark icons if we're in light theme
                systemUiController.setSystemBarsColor(
                    color = if (useDarkColor) PrimaryColorVariantDark else PrimaryColorVariantLight
                )
                // setStatusBarColor() and setNavigationBarColor() also exist
                onDispose {}
            }

            MovieAppTheme {
                MovieAppNavigation()
            }
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onStart() {
        super.onStart()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onStop() {
        super.onStop()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}

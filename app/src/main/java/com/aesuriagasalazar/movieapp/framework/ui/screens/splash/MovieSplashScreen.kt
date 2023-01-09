package com.aesuriagasalazar.movieapp.framework.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.aesuriagasalazar.movieapp.R
import com.aesuriagasalazar.movieapp.framework.ui.theme.MovieAppTheme
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun MovieSlashScreen(modifier: Modifier = Modifier, onTimeOut: () -> Unit) {

    val currentTime by rememberUpdatedState(newValue = onTimeOut)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary,
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_loading))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        LottieAnimation(composition = composition, progress = { progress })

        LaunchedEffect(key1 = Unit, block = {
            delay(2000)
            currentTime()
        })
    }
}


@Preview
@Composable
fun MovieSplashScreenPreview() {
    MovieAppTheme {
        MovieSlashScreen(onTimeOut = {})
    }
}
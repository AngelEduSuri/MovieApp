package com.aesuriagasalazar.movieapp.framework.ui.screens.splash

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aesuriagasalazar.movieapp.R
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieBackground
import com.aesuriagasalazar.movieapp.framework.ui.theme.MovieAppTheme
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieSlashScreen(modifier: Modifier = Modifier, onTimeOut: () -> Unit) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    val lazyListState = rememberLazyListState()
    var showTitle by remember { mutableStateOf(false) }
    var stateIndex by remember { mutableStateOf(-1) }
    val text = stringResource(id = R.string.app_name)
    val animationDelay = 150

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary,
                    )
                )
            )
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress })

        animateIntAsState(
            targetValue = stateIndex,
            animationSpec = tween(delayMillis = animationDelay * stateIndex)
        ) {
            if (it >= text.length - 1) {
                onTimeOut()
            }
        }

        AnimatedVisibility(visible = showTitle) {
            LazyRow(
                Modifier.fillMaxWidth().padding(top = 16.dp),
                state = lazyListState,
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(text.toList()) { index, item ->
                    Text(
                        text = item.toString(),
                        modifier = Modifier.animateEnterExit(
                            enter = fadeIn(
                                animationSpec = tween(delayMillis = animationDelay * index)
                            )
                        ),
                        style = MaterialTheme.typography.h5,
                        letterSpacing = 1.sp
                    )
                    stateIndex = (index * 1.7).toInt()
                }
            }
        }

        LaunchedEffect(key1 = Unit, block = {
            delay(1000)
            showTitle = true
        })
    }

}

@Preview
@Composable
fun MovieSplashScreenPreview() {
    MovieAppTheme {
        MovieBackground {
            MovieSlashScreen(onTimeOut = {})
        }
    }
}
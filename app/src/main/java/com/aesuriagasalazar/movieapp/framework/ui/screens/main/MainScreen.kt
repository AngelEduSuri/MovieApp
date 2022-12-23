package com.aesuriagasalazar.movieapp.framework.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.*
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieCard
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieTopBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.koinViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(onClickDrawer:() -> Unit, viewModel: MainViewModel = koinViewModel()) {

    val uiState = viewModel.uiState.collectAsState().value
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary,
                    )
                )
            )
    ) {
        MovieTopBar(title = "Trending Movies", onClick = onClickDrawer)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.movies.isEmpty()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.size(size = 80.dp),
                    strokeWidth = 10.dp
                )
            }
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 64.dp),
                count = uiState.movies.size,
                itemSpacing = 32.dp,
                state = pagerState
            ) {
                Box(
                    modifier = Modifier.graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(it).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                ) {
                    MovieCard(movie = uiState.movies[it])
                }
            }
        }
    }
}

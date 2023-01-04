package com.aesuriagasalazar.movieapp.framework.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.*
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.Movie
import com.aesuriagasalazar.movieapp.domain.ResultMovieData
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieCard
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieProgressIndicator
import com.aesuriagasalazar.movieapp.framework.ui.components.MovieTopBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@Composable
fun MainScreen(
    moviesState: ResultMovieData<List<Movie>>,
    currentGenre: Genre,
    isGridViewEnabled: Boolean,
    onClickDrawer: () -> Unit,
    onClickView: () -> Unit
) {

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
        MovieTopBar(
            title = currentGenre.name,
            isGridViewEnabled = isGridViewEnabled,
            onGenreClick = onClickDrawer,
            onViewClick = onClickView
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (moviesState) {
                is ResultMovieData.Error -> Text(
                    text = "${moviesState.error}",
                    textAlign = TextAlign.Center
                )
                ResultMovieData.Loading -> MovieProgressIndicator(
                    modifier = Modifier.size(size = 80.dp)
                )
                is ResultMovieData.Success -> PopularMovieList(
                    data = moviesState.data,
                    isGridViewEnabled = isGridViewEnabled
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PopularMovieList(
    modifier: Modifier = Modifier,
    data: List<Movie>,
    isGridViewEnabled: Boolean
) {

    val pagerState = rememberPagerState()
    val gridState = rememberLazyGridState()

    if (isGridViewEnabled) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            contentPadding = PaddingValues(all = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            state = gridState
        ) {
            items(data) {
                MovieCard(movie = it, heightCard = 280, isGridView = true)
            }
        }
    } else {
        HorizontalPager(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 64.dp),
            count = data.size,
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
                MovieCard(movie = data[it])
            }
        }
    }
}

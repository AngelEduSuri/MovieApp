package com.aesuriagasalazar.movieapp.framework.ui.screens.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aesuriagasalazar.movieapp.R
import com.aesuriagasalazar.movieapp.domain.*
import com.aesuriagasalazar.movieapp.framework.ui.components.*
import com.aesuriagasalazar.movieapp.framework.ui.theme.MovieAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel = koinViewModel(), onNavClick: () -> Unit) {

    val uiState = viewModel.uiState.collectAsState().value

    MovieBackground {
        when (val result = uiState.movieDataResult) {
            is ResultMovieData.Error -> Text(text = result.error.toString())
            ResultMovieData.Loading -> MovieProgressIndicator(modifier = Modifier.size(size = 80.dp))
            is ResultMovieData.Success -> {
                MovieDetailsContent(
                    movie = result.data,
                    scrollUp = uiState.appBarScroll,
                    onNavClick = onNavClick,
                    onScrollIndexChanged = viewModel::updateScrollPosition
                )
            }
        }
    }
}

@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movie: MovieDetails,
    scrollUp: Boolean,
    onNavClick: () -> Unit,
    onScrollIndexChanged: (Int) -> Unit
) {

    val scrollState = rememberLazyListState()

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }.collect {
            onScrollIndexChanged(it)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 56.dp),
            state = scrollState
        ) {
            item { Spacer(modifier = Modifier.height(height = 10.dp)) }
            item {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.height(height = 300.dp)) {
                        Card(
                            modifier = Modifier.aspectRatio(ratio = 16f / 9f),
                            shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 2.dp)),
                        ) {
                            MovieLoadImage(
                                imagePath = movie.backdropPath, description = movie.title
                            )
                        }
                        Card(
                            modifier = Modifier
                                .padding(start = 30.dp, bottom = 15.dp)
                                .height(height = 150.dp)
                                .aspectRatio(ratio = 2f / 3f)
                                .align(Alignment.BottomStart),
                            shape = MaterialTheme.shapes.medium,
                            elevation = 8.dp
                        ) {
                            MovieLoadImage(
                                imagePath = movie.posterPath, description = movie.title
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = movie.title, style = MaterialTheme.typography.h6)
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        HorizontalMovieDuration(movie = movie)
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        MovieCategoryList(categories = movie.genre)
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        MovieOverview(
                            movie = movie, title = stringResource(id = R.string.overview_title)
                        )
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        MovieCast(
                            cast = movie.cast.subList(
                                0, when {
                                    movie.cast.lastIndex > 15 -> movie.cast.lastIndex / 2
                                    else -> movie.cast.lastIndex
                                }
                            )
                        )
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        MovieData(movie = movie)
                        Spacer(modifier = Modifier.height(height = 16.dp))
                        MovieCompanies(movie = movie)
                    }
                }
            }
        }
        ScrollableAppBar(
            title = movie.title, scrollUpState = scrollUp, onNavClick = onNavClick
        )
    }
}

@Composable
fun ScrollableAppBar(
    title: String, modifier: Modifier = Modifier, scrollUpState: Boolean, onNavClick: () -> Unit
) {

    val position by animateFloatAsState(if (scrollUpState) -200f else 0f)

    Box(modifier = modifier.graphicsLayer { translationY = (position) }) {
        MovieTopBar(
            title = title,
            onNavClick = onNavClick,
            iconNav = Icons.Default.ArrowBack,
            textStyle = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun HorizontalMovieDuration(modifier: Modifier = Modifier, movie: MovieDetails) {

    val localColor = LocalContentColor.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(weight = 1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieRating(
                ratingSize = 45.dp,
                average = movie.average,
                percentStringSize = 8.sp,
                ratingTitleSize = 14.sp,
                ratingIndicator = 3.dp
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(
                text = stringResource(id = R.string.user_score), fontWeight = FontWeight.Bold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp)
                .width(width = 1.dp),
            color = MaterialTheme.colors.onPrimary.copy(alpha = 0.7f)
        )
        Row(
            modifier = Modifier
                .weight(weight = 1f)
                .height(intrinsicSize = IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = movie.release.toYearString())
            Divider(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(size = 6.dp),
                color = localColor.copy(alpha = 0.5f)
            )
            Text(text = movie.duration.toDurationString())
        }
    }
}

@Composable
fun MovieCategoryList(modifier: Modifier = Modifier, categories: List<Genre>) {

    Surface(
        modifier = modifier, color = MaterialTheme.colors.primaryVariant
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 18.dp)
        ) {
            items(items = categories) {
                Card(
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 16.dp)),
                    elevation = 8.dp,
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    border = BorderStroke(
                        width = 1.dp, color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f)
                    )
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieOverview(
    modifier: Modifier = Modifier, movie: MovieDetails, title: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        if (movie.tagline.isNotEmpty()) Text(text = movie.tagline, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = movie.overview, textAlign = TextAlign.Justify)
    }
}

@Composable
fun MovieCast(modifier: Modifier = Modifier, cast: List<Cast>) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = stringResource(R.string.cast),
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(items = cast) {
                Column(
                    modifier = Modifier.width(width = 85.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier.size(size = 120.dp), shape = CircleShape
                    ) {
                        MovieLoadImage(
                            imagePath = it.profile,
                            description = it.name,
                            contentScale = ContentScale.Crop,
                            imageError = when (it.gender) {
                                0 -> R.drawable.generic_person
                                1 -> R.drawable.female_person
                                2 -> R.drawable.male_person
                                else -> {
                                    R.drawable.loading_error
                                }
                            }
                        )
                    }
                    Text(
                        text = it.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = it.character,
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun MovieData(modifier: Modifier = Modifier, movie: MovieDetails) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(text = stringResource(R.string.status), fontWeight = FontWeight.Bold)
        Text(text = movie.status, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = stringResource(R.string.original_language), fontWeight = FontWeight.Bold)
        Text(text = movie.originalLanguage.toLanguageConvert(), fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = stringResource(R.string.budget), fontWeight = FontWeight.Bold)
        Text(text = movie.budget.toCurrency(), fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(text = stringResource(R.string.revenue), fontWeight = FontWeight.Bold)
        Text(text = movie.revenue.toCurrency(), fontWeight = FontWeight.Light)
    }
}

@Composable
fun MovieCompanies(modifier: Modifier = Modifier, movie: MovieDetails) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = stringResource(R.string.companies),
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(items = movie.companies) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.aspectRatio(ratio = 21f / 9f),
                ) {
                    Card(
                        modifier = Modifier
                            .height(height = 60.dp)
                            .aspectRatio(ratio = 21f / 9f),
                        shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 12.dp))
                    ) {
                        MovieLoadImage(
                            imagePath = it.logo,
                            description = it.name,
                            contentScale = ContentScale.FillWidth,
                            imageError = R.drawable.loading_error
                        )
                    }
                    Text(
                        text = it.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = it.country,
                        style = MaterialTheme.typography.subtitle2,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsContentPreview() {
    val cast = mutableListOf<Cast>()
    val genre = mutableListOf<Genre>()
    val companies = mutableListOf<Company>()

    repeat(times = 5) {
        genre.add(
            Genre(it, "Genero $it")
        )
        cast.add(
            Cast(id = it, "Cast $it", "Character $it", "", it)
        )
        companies.add(
            Company(id = it, logo = "", name = "Company $it", country = "US")
        )
    }
    MovieAppTheme {
        Scaffold(backgroundColor = MaterialTheme.colors.primary) {
            MovieBackground {
                MovieDetailsContent(movie = MovieDetails(
                    id = 1000,
                    title = "Movie Preview",
                    overview = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a viverra nunc, non gravida dui. Morbi porta, odio nec cursus molestie, eros odio tincidunt elit, quis sodales tortor arcu ut risus. Vivamus eget volutpat lorem. Suspendisse id lacus nisi.",
                    backdropPath = "",
                    budget = 12523525,
                    genre = genre,
                    originalLanguage = "en",
                    posterPath = "",
                    release = "2022-12-28",
                    duration = 160,
                    average = 7.324,
                    cast = cast,
                    tagline = "Lorem ipsum dolor sit amet",
                    status = "Released",
                    revenue = 12321123L,
                    companies = companies
                ), scrollUp = false, onNavClick = { /*TODO*/ }, onScrollIndexChanged = {})
            }
        }
    }
}

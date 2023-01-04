package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.domain.ResultMovieData

@Composable
fun MovieDrawerContent(
    currentGenre: Genre,
    onGenreClicked: (Genre) -> Unit,
    genreState: ResultMovieData<List<Genre>>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.primary
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            MovieProfile(
                modifier = Modifier
                    .height(height = 200.dp)
                    .fillMaxWidth()
            )
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(height = 8.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (genreState) {
                is ResultMovieData.Error -> {
                    item { Text(text = "${genreState.error}", textAlign = TextAlign.Center) }
                }
                ResultMovieData.Loading -> {
                    item {
                        MovieProgressIndicator(
                            modifier = Modifier.size(size = 50.dp),
                            strokeWidth = 5.dp
                        )
                    }
                }
                is ResultMovieData.Success -> {

                    item {
                        MovieCategory(
                            genre = Genre(0, "Trending Movies"),
                            currentGenre = currentGenre,
                            onClick = onGenreClicked
                        )
                    }

                    items(genreState.data) {
                        MovieCategory(
                            genre = it,
                            onClick = onGenreClicked,
                            currentGenre = currentGenre
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MovieProfile(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(size = 100.dp)
                    .padding(all = 16.dp),
                tint = MaterialTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun MovieCategory(genre: Genre, currentGenre: Genre, onClick: (Genre) -> Unit) {

    val transition = updateTransition(targetState = currentGenre, "Color Indicator")

    val indicatorColor by transition.animateColor(label = "Left Indicator Color") {
        if (it.id == genre.id) MaterialTheme.colors.secondaryVariant else Color.Transparent
    }

    val backgroundColor by transition.animateColor(label = "Background Color") {
        if (it.id == genre.id) MaterialTheme.colors.primaryVariant.copy(alpha = 0.8f) else Color.Transparent
    }

    val fontWeight by transition.animateInt(label = "Font Weight") {
        if (it.id == genre.id) 700 else 300
    }

    val fontAlpha by transition.animateFloat(label = "Font Color Alpha") {
        if (it.id == genre.id) 1f else 0.7f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .background(backgroundColor)
            .clickable {
                onClick(genre)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(weight = 1f)) {
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width = 5.dp),
                    color = indicatorColor
                )
            }
            Text(
                text = genre.name,
                modifier = Modifier.weight(weight = 4f),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(weight = fontWeight),
                    color = MaterialTheme.colors.onBackground.copy(alpha = fontAlpha)
                )
            )
        }
    }
}

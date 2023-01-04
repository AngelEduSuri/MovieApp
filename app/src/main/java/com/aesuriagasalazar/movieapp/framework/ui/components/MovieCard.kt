package com.aesuriagasalazar.movieapp.framework.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aesuriagasalazar.movieapp.domain.Movie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    heightCard: Int = 500,
    elevation: Int = 16,
    movie: Movie,
    isGridView: Boolean = false
) {

    val size = if (isGridView) 6.dp else 12.dp
    val titleSize = if (isGridView) 13.sp else 18.sp
    val ratingSize = if (isGridView) 33.dp else 50.dp
    val genreTitleSize = if (isGridView) 11.sp else 13.sp
    val ratingTitleSize = if (isGridView) 11.sp else 16.sp
    val percentStringSize = if (isGridView) 7.sp else 10.sp
    val ratingIndicator = if (isGridView) 2.dp else 5.dp

    Log.i("leer", "Rating: ${(movie.average / 10).toFloat()}")
    Column(
        modifier = modifier.height(height = heightCard.dp)
    ) {
        Card(
            modifier = Modifier.aspectRatio(ratio = 2f / 3f),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = size)),
            elevation = elevation.dp
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(height = size))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(weight = 0.7f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6.copy(fontSize = titleSize),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.genres.map { it.name }.toString()
                        .replace(oldValue = "[", newValue = "")
                        .replace(oldValue = "]", newValue = ""),
                    style = MaterialTheme.typography.body2.copy(fontSize = genreTitleSize)
                )
            }
            Box(
                modifier = Modifier
                    .weight(weight = 0.3f),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(size = ratingSize),
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append(text = "${(movie.average * 10).toInt()}")
                                withStyle(
                                    style = SpanStyle(
                                        baselineShift = BaselineShift.Superscript,
                                        color = MaterialTheme.colors.onPrimary,
                                        fontSize = percentStringSize
                                    )
                                ) {
                                    append(text = "%")
                                }
                            },
                            style = MaterialTheme.typography.body1.copy(fontSize = ratingTitleSize)
                        )
                        MovieProgressIndicator(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.secondary,
                            strokeWidth = ratingIndicator,
                            progress = (movie.average / 10).toFloat()
                        )
                    }
                }
            }
        }
    }
}
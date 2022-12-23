package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aesuriagasalazar.movieapp.domain.Movie

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    heightCard: Int = 500,
    elevation: Int = 16,
    movie: Movie
) {

    Column(modifier = modifier.height(height = heightCard.dp)) {
        Card(
            modifier = Modifier.weight(weight = 1f),
            shape = MaterialTheme.shapes.small.copy(all = CornerSize(size = 12.dp)),
            elevation = elevation.dp
        ) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(height = 12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = movie.genres.toString(),
                    style = MaterialTheme.typography.body2
                )
            }
            Box(
                modifier = Modifier
                    .size(size = 50.dp)
                    .clip(shape = RoundedCornerShape(percent = 100))
                    .background(MaterialTheme.colors.secondaryVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(text = movie.average.toString())
            }
        }
    }
}
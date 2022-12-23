package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aesuriagasalazar.movieapp.domain.Genre
import com.aesuriagasalazar.movieapp.framework.ui.theme.MovieAppTheme

@Composable
fun MovieDrawerContent(categories: List<Genre>, genreSelectedId: Long = 0) {

    var genreSelected by remember { mutableStateOf(1L) }

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
        LazyColumn {
            item {
                MovieProfile(
                    modifier = Modifier
                        .height(height = 250.dp)
                        .fillParentMaxWidth()
                )
            }
            item {
                Divider(
                    thickness = 1.dp,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(height = 8.dp))
            }
            items(items = categories) {
                MovieCategory(
                    genre = it,
                    onClick = { id ->
                        genreSelected = id
                    },
                    genreSelectedId = genreSelected
                )
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
            shape = RoundedCornerShape(percent = 100)
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
private fun MovieCategory(genre: Genre, genreSelectedId: Long, onClick: (Long) -> Unit) {

    val transition = updateTransition(targetState = genreSelectedId, "Color Indicator")

    val indicatorColor by transition.animateColor(label = "Left Indicator Color") {
        if (it == genre.id) MaterialTheme.colors.secondaryVariant else Color.Transparent
    }

    val backgroundColor by transition.animateColor(label = "Background Color") {
        if (it == genre.id) MaterialTheme.colors.primary else Color.Transparent
    }

    val fontWeight by transition.animateInt(label = "Font Weight") {
        if (it == genre.id) 700 else 300
    }

    val fontAlpha by transition.animateFloat(label = "Font Color Alpha") {
        if (it == genre.id) 1f else 0.7f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .background(backgroundColor)
            .clickable {
                onClick(genre.id)
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

@Preview(widthDp = 250, heightDp = 100)
@Composable
fun MovieCategory() {
    MovieAppTheme {
        MovieCategory(
            genre = Genre(12, "Action"),
            onClick = {},
            genreSelectedId = 3
        )
    }
}
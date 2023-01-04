package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovieProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondary,
    strokeWidth: Dp = 10.dp,
    progress: Float? = null
) {

    if (progress != null) {
        CircularProgressIndicator(
            color = color,
            modifier = modifier,
            strokeWidth = strokeWidth,
            progress = progress
        )
    } else {
        CircularProgressIndicator(
            color = color,
            modifier = modifier,
            strokeWidth = strokeWidth
        )
    }
}
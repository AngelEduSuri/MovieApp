package com.aesuriagasalazar.movieapp.framework.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.aesuriagasalazar.movieapp.R

@Composable
fun MovieLoadImage(
    imagePath: String,
    description: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    @DrawableRes imageError: Int = R.drawable.loading_error
) {

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        )
    )

    SubcomposeAsyncImage(
        model = imagePath,
        contentDescription = description,
        loading = {
            Icon(
                modifier = Modifier
                    .graphicsLayer { rotationZ = -angle }
                    .padding(all = 12.dp),
                painter = painterResource(id = R.drawable.loading_icon),
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        },
        error = {
            Image(
                painter = painterResource(id = imageError),
                contentDescription = null
            )
        },
        contentScale = contentScale
    )
}
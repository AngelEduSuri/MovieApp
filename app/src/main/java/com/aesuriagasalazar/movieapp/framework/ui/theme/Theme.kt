package com.aesuriagasalazar.movieapp.framework.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = PrimaryColorDark,
    primaryVariant = PrimaryColorVariantDark,
    secondary = SecondaryColorDark,
    secondaryVariant = SecondaryColorVariantDark,
    onPrimary = Color.White,
    background = PrimaryColorVariantLight,
    onBackground = Color.White,
    onSecondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = PrimaryColorLight,
    primaryVariant = PrimaryColorVariantLight,
    secondary = SecondaryColorLight,
    secondaryVariant = SecondaryColorVariantLight,
    onPrimary = Color.White,
    background = PrimaryColorVariantDark,
    onBackground = Color.White,
    onSecondary = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MovieAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
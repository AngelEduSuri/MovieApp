package com.aesuriagasalazar.movieapp.framework.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aesuriagasalazar.movieapp.R

val Merriweahter = FontFamily(
    Font(R.font.merriweather_sans_light, FontWeight.Light),
    Font(R.font.merriweather_sans_medium, FontWeight.Medium),
    Font(R.font.merriweather_sans_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Merriweahter,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    body2 = TextStyle(
        fontFamily = Merriweahter,
        fontWeight = FontWeight.Light,
        fontSize = 13.sp
    )
    ,
    h6 = TextStyle(
        fontFamily = Merriweahter,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = Merriweahter,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h4 = TextStyle(
        fontFamily = Merriweahter,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    subtitle2 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
package co.nes.parceltestkmp.ui.theme.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BlueLinearGradient =  Brush.linearGradient(
    colors = listOf(
        Color(0xFF176EF2),
        Color(0xFF196EEE),
    ),
)

val LightAspenColors = AspenColors(
    primary = BlueLinearGradient,
    onPrimary = Color.White,
    secondary = Color(0xFFB8B8B8),
    onSecondary = Color.Black,
    tertiary = Color(0xFFC9C9C9),
    onTertiary = Color(0xFFB8B8B8),
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
)

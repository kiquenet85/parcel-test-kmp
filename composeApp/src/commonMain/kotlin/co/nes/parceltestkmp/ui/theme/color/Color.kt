package co.nes.parceltestkmp.ui.theme.color

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF176EF2)

val BlueLinearGradient =  Brush.linearGradient(
    colors = listOf(
        Color(0xFF176EF2),
        Color(0xFF196EEE),
    ),
)

val GraySecondary = Color(0xFFB8B8B8)
val GrayOnSecondary = Color(0xFF606060)
val GrayTertiary = Color(0xFFF3F8FE)
val GrayOnTertiary = Color(0xFFB8B8B8)
val BottomBarColor = Color(0xFFFEFEFE)

val LightAspenColors = AspenColors(
    primary = Blue,
    onPrimary = Color.White,
    secondary = GraySecondary,
    onSecondary = GrayOnSecondary,
    tertiary = GrayTertiary,
    onTertiary = GrayOnTertiary,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    primaryGradient = BlueLinearGradient,
    bottomBarColor = BottomBarColor,
)

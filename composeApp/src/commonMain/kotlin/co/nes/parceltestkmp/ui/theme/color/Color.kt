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

val BlueLinearGradientWithOpacity =  Brush.linearGradient(
    colors = listOf(
        Color(0xFF176EF2).copy(alpha = 0.18f),
        Color(0xFF196EEE).copy(alpha = 0.18f),
    ),
)

val GraySecondary = Color(0xFFB8B8B8)
val GrayOnSecondary = Color(0xFF606060)
val GrayTertiary = Color(0xFFF3F8FE)
val GrayOnTertiary = Color(0xFFB8B8B8)
val BottomBarColor = Color(0xFFFEFEFE)
val BackgroundPrimary = Color(0xFFF5F5F5)
val BackgroundSecondary = Color(0xFF4D5652)
val Yellow = Color(0xFFF8D675)
val HeadLine = Color(0xFF3A544F)
val Golden = Color(0xFF84ABE4)

val LightAspenColors = AspenColors(
    primary = Blue,
    onPrimary = Color.White,
    secondary = GraySecondary,
    onSecondary = GrayOnSecondary,
    tertiary = GrayTertiary,
    onTertiary = GrayOnTertiary,
    background = BackgroundPrimary,
    onBackground = Color.Black,
    backgroundSecondary = BackgroundSecondary,
    primaryGradient = BlueLinearGradient,
    bottomBarColor = BottomBarColor,
    secondaryGradient = BlueLinearGradientWithOpacity,
    onBackgroundSecondary = Color.White,
    headLine = HeadLine,
    startIconColor = Yellow,
    trendingIconColor = Golden,
)

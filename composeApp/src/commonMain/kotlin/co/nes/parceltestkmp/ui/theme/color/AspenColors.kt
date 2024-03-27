package co.nes.parceltestkmp.ui.theme.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class AspenColors(
    primary: Color,
    onPrimary: Color,
    secondary: Color,
    onSecondary: Color,
    tertiary: Color,
    onTertiary: Color,
    background: Color,
    onBackground: Color,
    backgroundSecondary: Color,
    onBackgroundSecondary: Color,
    bottomBarColor: Color,
    primaryGradient: Brush,
    secondaryGradient: Brush,
    headLine: Color,
    startIconColor: Color,
    trendingIconColor: Color,
) {
    var primary by mutableStateOf(primary)
        internal set

    var onPrimary by mutableStateOf(onPrimary)
        internal set

    var secondary by mutableStateOf(secondary)
        internal set

    var onSecondary by mutableStateOf(onSecondary)
        internal set

    var tertiary by mutableStateOf(tertiary)
        internal set

    var onTertiary by mutableStateOf(onTertiary)
        internal set

    var background by mutableStateOf(background)
        internal set

    var onBackground by mutableStateOf(onBackground)
        internal set

    var backgroundSecondary by mutableStateOf(backgroundSecondary)
        internal set

    var onBackgroundSecondary by mutableStateOf(onBackgroundSecondary)
        internal set

    var bottomBarColor by mutableStateOf(bottomBarColor)
        internal set

    var primaryGradient by mutableStateOf(primaryGradient)
        internal set

    var secondaryGradient by mutableStateOf(secondaryGradient)
        internal set
    var headLine by mutableStateOf(headLine)
        internal set

    var startIconColor by mutableStateOf(startIconColor)
        internal set

    var trendingIconColor by mutableStateOf(trendingIconColor)
        internal set
}

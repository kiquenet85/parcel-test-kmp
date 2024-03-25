package co.nes.parceltestkmp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import co.nes.parceltestkmp.ui.theme.color.AspenColors
import co.nes.parceltestkmp.ui.theme.color.LightAspenColors
import co.nes.parceltestkmp.ui.theme.type.AspenTypography
import androidx.compose.material3.Typography as Typography3

object AspenTheme {

    private val LocalAspenColors = staticCompositionLocalOf {
        LightAspenColors
    }

    private val LocalTypography = staticCompositionLocalOf { AspenTypography }

    @Composable
    fun getAspenColors(): ProvidableCompositionLocal<AspenColors> {
        return LocalAspenColors
    }

    @Composable
    fun getTypography(): ProvidableCompositionLocal<Typography3> {
        return LocalTypography
    }

    val aspenColors: AspenColors
        @Composable
        get() = LocalAspenColors.current

    val typography: Typography3
        @Composable
        get() = LocalTypography.current
}

@Composable
fun AspenTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        AspenTheme.getAspenColors() provides AspenTheme.aspenColors,
        AspenTheme.getTypography() provides AspenTheme.typography,
    ) {
        content()
    }
}

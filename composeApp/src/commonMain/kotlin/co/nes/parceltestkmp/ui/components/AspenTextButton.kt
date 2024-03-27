package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenTextButton(
    text: String,
    enabled: Boolean = true,
    style: TextStyle = AspenTheme.typography.labelLarge,
    color: Color = AspenTheme.colors.onBackground,
    onClick: () -> Unit,
) {
    AspenText(
        text = text,
        style = style,
        color = color,
        modifier = Modifier.clickable(enabled = enabled) {
            onClick()
        },
    )
}

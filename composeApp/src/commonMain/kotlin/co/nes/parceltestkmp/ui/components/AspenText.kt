package co.nes.parceltestkmp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = AspenTheme.typography.bodyLarge,
    color: Color = AspenTheme.colors.onBackground,
) {
    Text(
        text,
        modifier = modifier,
        style = style.copy(
            color = color,
        ),
    )
}
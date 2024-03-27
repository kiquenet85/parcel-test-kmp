package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    height: Int = 52,
    width: Int? = null,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    content: @Composable () -> Unit,
) {

    val widthModifier = if (width != null) {
        Modifier.width(width.dp)
    } else {
        Modifier.fillMaxWidth()
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .height(height.dp)
            .then(widthModifier),
        shape = shape,
        contentPadding = PaddingValues(),
    ){
        Box(
            modifier = Modifier.fillMaxSize().background(
                brush = AspenTheme.colors.primaryGradient,
            ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

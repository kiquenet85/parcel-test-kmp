package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    content: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(32.dp)
            .height(60.dp)
            .fillMaxWidth(),
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

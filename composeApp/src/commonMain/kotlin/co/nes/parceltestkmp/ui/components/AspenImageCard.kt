package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun AspenImageCard(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    modifierOut: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    onClick: (() -> Unit)? = null,
    contentOut: (@Composable () -> Unit)? = null,
    contentIn: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifierOut,
    ) {
        Card(
            onClick = {
                onClick?.invoke()
            },
            modifier = modifier,
            shape = shape,
        ) {
            Box {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                contentIn?.invoke()
            }
        }
        contentOut?.invoke()
    }
}

package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import co.nes.parceltestkmp.ui.theme.AspenTheme.typography
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.arrow_right

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BottomBarDetail(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.background)
            .padding(vertical = 24.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            AspenText(
                "Price",
                style = typography.labelLarge,
            )
            AspenText(
                "$199",
                style = typography.titleMedium,
                color = Color(0xFF2DD7A4),
            )
        }

        AspenButton(
            height = 56,
            width = 223,
            onClick = {}
        ) {
            Row {
                AspenText(
                    text = "Book Now",
                    color = colors.onPrimary,
                    style = typography.bodyLarge,
                )
                Icon(
                    painter = painterResource(Res.drawable.arrow_right),
                    contentDescription = "Book Now",
                    tint = colors.onPrimary,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenDropdownMenu(
    expanded: Boolean,
    items: List<String>,
    onClick: (String) -> Unit,
    onDismissRequest: () -> Unit = {},
) {
    DropdownMenu(
        modifier = Modifier.width(106.dp),
        expanded = expanded,
        onDismissRequest = {
            onDismissRequest()
        },
    ) {
        items.forEach { label ->
            AspenDropDownMenuItem(
                text = label,
                onClick = onClick,
            )
        }
    }
}

@Composable
fun AspenDropDownMenuItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit,
) {
    DropdownMenuItem(
        modifier = modifier,
        text = {
            AspenText(
                text,
                style = AspenTheme.typography.bodySmall,
                color = AspenTheme.colors.onBackground,
            )
        },
        onClick = {
            onClick(text)
        }
    )
}

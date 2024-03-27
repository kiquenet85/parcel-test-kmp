package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import co.nes.parceltestkmp.ui.theme.AspenTheme.typography

@Composable
fun AspenHorizontalList(
    modifier: Modifier = Modifier,
    title: String,
    textButton: String? = null,
    items: List<AspenHorizontalListValue>,
    content: @Composable (Int, AspenHorizontalListValue) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AspenText(
            title,
            style = typography.titleSmall,
        )
        textButton?.let {
            AspenTextButton(
                text = textButton,
                color = colors.primary,
                onClick = { },
            )
        }
    }

    LazyRow {
        itemsIndexed(items, key = {
            _, item -> item.name
        }) { index, item ->
            content(index, item)
        }
    }
}

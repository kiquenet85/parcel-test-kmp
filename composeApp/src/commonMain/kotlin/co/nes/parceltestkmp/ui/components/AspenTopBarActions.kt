package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenTopBarActions(
    modifier: Modifier = Modifier,
    layout: AspenTopBarLayout = AspenTopBarLayout.Home,
    onClick: (String) -> Unit,
    items: List<String>? = null,
    currentSelection: String? = null,
    onDismissRequest: () -> Unit = {},
) {
    when(layout) {
        AspenTopBarLayout.Home -> {
            val expanded = remember { mutableStateOf(false) }
            val currentSelectionHome = currentSelection ?: ""
            val itemsHome = items ?: emptyList()

            Box(
                modifier = modifier
                    .wrapContentSize(Alignment.TopEnd)
                    .padding(end = 16.dp)
                    .clickable {
                        expanded.value = true
                    }
            ) {
                Row {
                    Icon(
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp),
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Place",
                        tint = AspenTheme.colors.primary,
                    )
                    AspenText(
                        text = currentSelectionHome,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .height(15.dp)
                            .widthIn(min = 64.dp),
                        style = AspenTheme.typography.bodySmall,
                        color = AspenTheme.colors.onSecondary,
                    )
                    Icon(
                        imageVector = if(expanded.value) Icons.Default.KeyboardArrowUp
                        else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp),
                        tint = AspenTheme.colors.primary,
                    )
                }
                AspenDropdownMenu(
                    expanded = expanded.value,
                    items = itemsHome,
                    onClick = {
                        onClick(it)
                        expanded.value = false
                    },
                    onDismissRequest = {
                        expanded.value = false
                        onDismissRequest()
                    },
                )
            }
        }

        AspenTopBarLayout.Detail -> {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(40.dp)
                    .width(40.dp)
                    .background(AspenTheme.colors.tertiary)
                    .clickable { onClick("") },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = AspenTheme.colors.secondary,
                )
            }
        }
    }
}

package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import co.nes.parceltestkmp.ui.theme.AspenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspenTopBar() {
    val items = listOf("Aspen, USA", "California, USA")
    val currentSelection = remember { mutableStateOf(items[0]) }

    TopAppBar(
        title = {
            Column{
                AspenText(
                    "Explore",
                    style = AspenTheme.typography.bodyMedium,
                )
                AspenText(
                    "Aspen",
                    style = AspenTheme.typography.titleLarge,
                )
            }
        },
        actions = {
            AspenTopBarActions(
                items = items,
                currentSelection = currentSelection.value,
                onClick = { currentSelection.value = it },
            )
        }
    )
}

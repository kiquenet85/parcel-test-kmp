package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AspenTopBar(
    modifier: Modifier = Modifier,
    layout: AspenTopBarLayout = AspenTopBarLayout.Home,
    onBackClicked: () -> Unit
) {
    when(layout) {
        AspenTopBarLayout.Home -> {
            val items = listOf("Aspen, USA", "California, USA")
            val currentSelection = remember { mutableStateOf(items[0]) }

            TopAppBar(
                modifier = modifier,
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

        AspenTopBarLayout.Detail -> {
            TopAppBar(
                modifier = modifier.height(100.dp),
                title = {},
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, top = 32.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        AspenTopBarActions(
                            layout = layout,
                            onClick = { onBackClicked()},
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        }
    }
}

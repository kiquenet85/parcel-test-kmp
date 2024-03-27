package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.ui.theme.AspenTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AspenBottomBar(
    bottomTabTitles: List<AspenBottomTab>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth()
            .height(72.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                shadowElevation = 2.2f
            }
            .clip(
                RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)
            ),
        backgroundColor = AspenTheme.colors.bottomBarColor,
    ) {
        bottomTabTitles.forEachIndexed { index, item ->
            val startPadding = if (index == 0) 72.dp else 0.dp
            val endPadding = if (index == bottomTabTitles.size - 1) 72.dp else 0.dp

            BottomNavigationItem(
                modifier = Modifier
                    .padding(
                        start = startPadding, end = endPadding,
                        top = 24.dp, bottom = 24.dp
                    ),
                selectedContentColor = AspenTheme.colors.primary,
                unselectedContentColor = AspenTheme.colors.secondary,
                icon = {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.width(24.dp).height(24.dp),
                            tint = if (selectedIndex == index) AspenTheme.colors.primary
                            else AspenTheme.colors.secondary
                        )
                    }
                },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
            )
        }
    }
}

package co.nes.parceltestkmp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.feature.place.explore.ExploreTab
import co.nes.parceltestkmp.ui.theme.AspenTheme

@Composable
fun AspenContentTabs(
    selectedTab: Int,
    tabTitles: List<ExploreTab>,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    ScrollableTabRow(
        modifier = modifier.fillMaxWidth().height(41.dp),
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp,
        indicator = @Composable { tabPositions: List<TabPosition> ->
            Box(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                    .fillMaxWidth().height(41.dp)
                    .clip(RoundedCornerShape(33.dp))
                    .background(
                        brush = AspenTheme.colors.secondaryGradient,
                    ).clipToBounds(),
            )
        },
        divider = {}
    ) {
        tabTitles.forEachIndexed { index, value ->
            Tab(
                modifier = Modifier
                    .fillMaxWidth().height(41.dp)
                    .clip(RoundedCornerShape(33.dp)).clipToBounds(),
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = { Text(value.title) },
                selectedContentColor = AspenTheme.colors.primary,
                unselectedContentColor = AspenTheme.colors.secondary
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        content()
    }
}

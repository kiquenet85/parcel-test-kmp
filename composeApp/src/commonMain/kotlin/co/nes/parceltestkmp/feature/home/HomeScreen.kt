package co.nes.parceltestkmp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.feature.home.ExploreTab.Companion.exploreTabs
import co.nes.parceltestkmp.feature.place.data.remote.VacationInfoRemoteImpl.Companion.tempData
import co.nes.parceltestkmp.feature.place.detail.DetailScreen
import co.nes.parceltestkmp.feature.place.horizontalList.PlaceHorizontalList
import co.nes.parceltestkmp.ui.components.AspenBottomBar
import co.nes.parceltestkmp.ui.components.AspenBottomTab
import co.nes.parceltestkmp.ui.components.AspenBottomTab.Companion.bottomTabTitles
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.ui.components.AspenSearchBar
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.components.AspenTopBar
import co.nes.parceltestkmp.ui.theme.AspenTheme

class HomeScreen : ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow
    val showTopBar = remember { mutableStateOf(true) }
    val selectedBottomBarIndex = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AspenTheme.colors.background
            ),
        topBar = { if (showTopBar.value) AspenTopBar() },
        bottomBar = {
            AspenBottomBar(
                bottomTabTitles,
                selectedIndex = selectedBottomBarIndex.value
            ) {
                selectedBottomBarIndex.value = it
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,  // Adding left padding
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            when (val currentBottomBarTabIdx = selectedBottomBarIndex.value) {
                AspenBottomTab.Explore.idx -> {
                    showTopBar.value = true
                    exploreSection(innerPadding, navigator)
                }

                else -> {
                    showTopBar.value = false
                    AspenText("${bottomTabTitles[currentBottomBarTabIdx].title} content")

                    AspenButton(onClick = {
                        navigator.pop()
                    }) {
                        AspenText(
                            "Go to Splash Screen",
                            color = AspenTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
})

@Composable
private fun exploreSection(innerPadding: PaddingValues, navigator: Navigator) {
    AspenSearchBar(
        query = "Find things to do",
        modifier = Modifier.fillMaxWidth()
            .padding(end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp)
    )

    val selectedTab = remember { mutableStateOf(0) }

    ContentTabs(
        selectedTab = selectedTab.value,
        tabTitles = exploreTabs,
        onTabSelected = { newSelection -> selectedTab.value = newSelection }
    ) {
        when (val newValue = selectedTab.value) {
            ExploreTab.Place.idx -> PlaceHorizontalList(
                tempData.places.popular,
                { navigator.push(DetailScreen()) },
                tempData.places.recommended,
                { navigator.push(DetailScreen()) }
            )

            else -> Text("${exploreTabs[newValue].title} content")
        }
    }
}

@Composable
fun ContentTabs(
    selectedTab: Int,
    tabTitles: List<ExploreTab>,
    onTabSelected: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp
    ) {
        tabTitles.forEachIndexed { index, value ->
            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = { Text(value.title) }
            )
        }
    }

    val scrollState = rememberScrollState()
    Column(
        Modifier.fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        content()
    }
}

sealed class ExploreTab(val idx: Int, val title: String) {
    data object Place : ExploreTab(0, "Place")
    data object Hotel : ExploreTab(1, "Hotel")
    data object Food : ExploreTab(2, "Food")
    data object Adventure : ExploreTab(3, "Adventure")
    data object Activities : ExploreTab(4, "Activities")

    companion object {
        val exploreTabs =
            listOf(Place, Hotel, Food, Adventure, Activities)
    }
}
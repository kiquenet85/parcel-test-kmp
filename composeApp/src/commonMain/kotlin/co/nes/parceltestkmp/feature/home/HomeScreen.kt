package co.nes.parceltestkmp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.feature.home.BottomTab.Companion.bottomTabTitles
import co.nes.parceltestkmp.feature.home.ExploreTab.Companion.exploreTabs
import co.nes.parceltestkmp.feature.place.data.remote.VacationInfoRemoteImpl.Companion.tempData
import co.nes.parceltestkmp.feature.place.detail.DetailScreen
import co.nes.parceltestkmp.feature.place.horizontalList.PlaceHorizontalList

class HomeScreen : ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow
    val showTopBar = remember { mutableStateOf(true) }
    val selectedBottomBarIndex = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (showTopBar.value) TopBar() },
        bottomBar = {
            BottomBar(
                bottomTabTitles,
                selectedIndex = selectedBottomBarIndex.value
            ) { selectedBottomBarIndex.value = it }
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
                )
        ) {

            when (val currentBottomBarTabIdx = selectedBottomBarIndex.value) {
                BottomTab.Explore.idx -> {
                    showTopBar.value = true
                    exploreSection(innerPadding, navigator)
                }

                else -> {
                    showTopBar.value = false
                    Text("${bottomTabTitles[currentBottomBarTabIdx].title} content")

                    Button(onClick = {
                        navigator.pop()
                    }) {
                        Text("Go to Splash Screen")
                    }
                }
            }
        }
    }
})

@Composable
private fun exploreSection(innerPadding: PaddingValues, navigator: Navigator) {
    SearchBar(
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

@Composable
fun SearchBar(modifier: Modifier) {
    val text: MutableState<String> = remember { mutableStateOf("") }
    TextField(
        modifier = modifier,
        value = TextFieldValue(text.value),
        onValueChange = { text.value = it.text },
        label = { Text("Search") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Explore Aspen") },
        actions = {
            val items = listOf("California", "USA")
            val expanded = remember { mutableStateOf(false) }
            val currentSelection = remember { mutableStateOf(items[0]) }

            Box(
                Modifier.wrapContentSize(Alignment.TopEnd)
                    .padding(end = 16.dp)
            ) {
                Button(onClick = {
                    expanded.value = true
                }) {
                    Text(text = currentSelection.value)
                }

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                ) {
                    items.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = label) },
                            onClick = {
                                currentSelection.value = label
                                expanded.value = false
                            })
                    }
                }
            }
        }
    )
}

@Composable
fun BottomBar(bottomTabTitles: List<BottomTab>, selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    BottomNavigation {
        bottomTabTitles.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text(item.title) },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

sealed class BottomTab(val idx: Int, val title: String) {
    data object Explore : BottomTab(0, "Explore")
    data object Bookings : BottomTab(1, "Bookings")
    data object Favorite : BottomTab(2, "Favorite")
    data object Profile : BottomTab(3, "Profile")

    companion object {
        val bottomTabTitles =
            listOf(Explore, Bookings, Favorite, Profile)
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
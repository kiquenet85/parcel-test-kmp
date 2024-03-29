package co.nes.parceltestkmp.feature.place.explore

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListCommand
import co.nes.parceltestkmp.feature.place.detail.DetailScreen
import co.nes.parceltestkmp.feature.place.location_list.PopularList
import co.nes.parceltestkmp.feature.place.location_list.RecommendedList
import co.nes.parceltestkmp.ui.components.AspenContentTabs
import co.nes.parceltestkmp.ui.components.AspenSearchBar
import kotlinx.coroutines.flow.onEach

@Composable
internal fun ExploreSection(innerPadding: PaddingValues, navigator: Navigator, homeViewModel: HomeViewModel) {

    val placeState = homeViewModel.viewState.collectAsState()

    homeViewModel.viewCommand.onEach {
        it.consume { command ->
            when (command) {
                PlaceListCommand.None -> Unit
                PlaceListCommand.ExitScreen -> navigator.pop()
                PlaceListCommand.SelectPlaceList -> navigator.push(DetailScreen(homeViewModel))
            }
        }
    }.collectAsState(PlaceListCommand.None)

    AspenSearchBar(
        query = "Find things to do",
        modifier = Modifier.fillMaxWidth()
            .padding(end = innerPadding.calculateEndPadding(LayoutDirection.Ltr) + 16.dp)
    )

    val selectedTab = remember { mutableStateOf(0) }

    AspenContentTabs(
        selectedTab = selectedTab.value,
        tabTitles = ExploreTab.exploreTabs,
        onTabSelected = { newSelection -> selectedTab.value = newSelection },
        modifier = Modifier.padding(top = 32.dp)
    ) {
        when (val newValue = selectedTab.value) {
            ExploreTab.Place.idx -> {
                PopularList(
                    popular = placeState.value.popular,
                    onPopularItemClicked = {
                        homeViewModel.toPlaceDetails(it)
                    },
                )

                RecommendedList(
                    recommended = placeState.value.recommended,
                    onRecommendedItemClicked = { homeViewModel.toPlaceDetails(it) },
                )
            }

            else -> Text("${ExploreTab.exploreTabs[newValue].title} content")
        }
    }
}

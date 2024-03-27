package co.nes.parceltestkmp.feature.place.explore

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteImpl
import co.nes.parceltestkmp.feature.place.detail.DetailScreen
import co.nes.parceltestkmp.feature.place.location_list.PopularList
import co.nes.parceltestkmp.feature.place.location_list.RecommendedList
import co.nes.parceltestkmp.ui.components.AspenContentTabs
import co.nes.parceltestkmp.ui.components.AspenSearchBar

@Composable
fun ExploreSection(innerPadding: PaddingValues, navigator: Navigator) {
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
                    popular = PlaceRemoteImpl.tempData.places.popular,
                    onPopularItemClicked = { navigator.push(DetailScreen()) },
                )

                RecommendedList(
                    recommended = PlaceRemoteImpl.tempData.places.recommended,
                    onRecommendedItemClicked = { navigator.push(DetailScreen()) },
                )
            }

            else -> Text("${ExploreTab.exploreTabs[newValue].title} content")
        }
    }
}

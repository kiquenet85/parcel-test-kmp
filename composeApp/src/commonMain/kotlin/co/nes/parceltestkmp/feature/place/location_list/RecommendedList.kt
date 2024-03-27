package co.nes.parceltestkmp.feature.place.location_list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.feature.place.model.Place
import co.nes.parceltestkmp.ui.components.AspenHorizontalList
import co.nes.parceltestkmp.ui.components.AspenHorizontalListValue
import co.nes.parceltestkmp.ui.components.AspenImageCard
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import co.nes.parceltestkmp.ui.theme.AspenTheme.typography
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.trending_up

@Composable
fun RecommendedList(
    recommended: List<Place>,
    onRecommendedItemClicked: (place: Place) -> Unit,
) {

    AspenHorizontalList(
        modifier = Modifier.padding(top = 32.dp),
        title = "Recommended",
        items = recommended.map { AspenHorizontalListValue.PlaceValue(it) },
    ) { _, item: AspenHorizontalListValue ->
        val place = (item as AspenHorizontalListValue.PlaceValue).place
        place?.let {
            RecommendedItem(
                place = it,
                onRecommendedItemClicked = onRecommendedItemClicked,
            )
        }

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalResourceApi::class)
@Composable
fun RecommendedItem(
    place: Place,
    onRecommendedItemClicked: (place: Place) -> Unit,
) {
    Card(
        modifier = Modifier
            .width(174.dp)
            .height(142.dp)
            .padding(top = 12.dp, end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onRecommendedItemClicked(place)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            AspenImageCard(
                modifier = Modifier
                    .width(166.dp)
                    .height(96.dp)
                    .padding(bottom = 6.dp),
                modifierOut = Modifier
                    .width(166.dp)
                    .height(96.dp),
                imageUrl = place.image,
                contentDescription = place.name,
                contentOut = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(end = 11.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .width(41.dp)
                                .height(16.dp)
                                .clip(
                                    shape = RoundedCornerShape(9.dp),
                                ).border(
                                    width = 1.dp,
                                    color = colors.onPrimary,
                                    shape = RoundedCornerShape(9.dp),
                                )
                                .background(
                                    color = colors.headLine,
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            AspenText(
                                "2N/3D",
                                style = typography.headlineSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                ),
                                color = colors.onPrimary,
                            )
                        }
                    }
                }
            )
            AspenText(
                place.name,
                style = typography.headlineMedium,
            )
            Row {
                Icon(
                    painter = painterResource(Res.drawable.trending_up),
                    contentDescription = "Trending up",
                    tint = colors.trendingIconColor,
                )
                AspenText(
                    "Hot deal",
                    modifier = Modifier.padding(start = 4.dp),
                    style = typography.headlineSmall,
                    color = colors.headLine
                )
            }
        }
    }
}

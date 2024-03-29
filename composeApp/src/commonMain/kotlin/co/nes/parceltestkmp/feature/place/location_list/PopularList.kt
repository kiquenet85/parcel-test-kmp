package co.nes.parceltestkmp.feature.place.location_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.ui.components.AspenHorizontalList
import co.nes.parceltestkmp.ui.components.AspenHorizontalListValue
import co.nes.parceltestkmp.ui.components.AspenImageCard
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import co.nes.parceltestkmp.ui.theme.AspenTheme.typography
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.heart_circle_activate
import parceltestkmp.composeapp.generated.resources.heart_circle_deactivate

@Composable
fun PopularList(
    popular: List<PlaceUI>,
    onPopularItemClicked: (place: PlaceUI) -> Unit,
) {
    AspenHorizontalList(
        title = "Popular",
        textButton = "See all",
        items = popular.map { AspenHorizontalListValue.PlaceValue(it) },
    ) { _, item: AspenHorizontalListValue ->
        val place = (item as AspenHorizontalListValue.PlaceValue).place
        place?.let {
            PopularItem(
                place = it,
                onPopularItemClicked = onPopularItemClicked,
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PopularItem(
    place: PlaceUI,
    onPopularItemClicked: (place: PlaceUI) -> Unit,
) {
    AspenImageCard(
        modifier = Modifier
            .width(188.dp)
            .height(240.dp)
            .padding(top = 12.dp, end = 28.dp),
        imageUrl = place.imageUrl,
        contentDescription = place.name,
        onClick = {
            onPopularItemClicked(place)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 16.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .widthIn(min = 52.dp)
                    .clip(
                        shape = RoundedCornerShape(59.dp)
                    )
                    .background(
                        color = colors.backgroundSecondary,
                    ).padding(
                        horizontal = 10.dp, vertical = 4.dp
                    )
            ) {
                AspenText(
                    text = place.name,
                    style = typography.headlineSmall,
                    color = colors.onBackgroundSecondary,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(min = 52.dp)
                        .clip(
                            shape = RoundedCornerShape(59.dp)
                        )
                        .background(
                            color = colors.backgroundSecondary,
                        ).padding(
                            horizontal = 10.dp, vertical = 4.dp
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = colors.startIconColor,
                            modifier = Modifier.size(16.dp),
                        )
                        AspenText(
                            text = "${place.rating}",
                            style = typography.headlineSmall,
                            color = colors.onBackgroundSecondary,
                        )
                    }
                }
                Icon(
                    painter = if (place.isFavorite) painterResource(Res.drawable.heart_circle_activate)
                        else painterResource(Res.drawable.heart_circle_deactivate),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

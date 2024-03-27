package co.nes.parceltestkmp.feature.place.facilities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.feature.place.facilities.Facilities.Companion.facilitiesOptions
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.theme.AspenTheme
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun FacilitiesList(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            AspenText(
                "Facilities",
                style = AspenTheme.typography.titleSmall,
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                itemsIndexed(
                    facilitiesOptions,
                    key = { _, facility -> facility.idx}
                ) { _, facility ->
                    FacilitiesItem(
                        facility = facility,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FacilitiesItem(
    facility: Facilities,
) {
    Box(
        modifier = Modifier
            .width(77.dp)
            .height(74.dp)
            .clip(
                shape = RoundedCornerShape(16.dp),
            ).background(
                color = colors.primary.copy(alpha = 0.05f),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(facility.icon),
                contentDescription = facility.name,
                modifier = Modifier.size(32.dp).padding(bottom = 6.dp),
                tint =  colors.onTertiary,
            )
            AspenText(
                facility.name,
                style = AspenTheme.typography.headlineSmall,
                color = colors.onTertiary,
            )
        }
    }
}


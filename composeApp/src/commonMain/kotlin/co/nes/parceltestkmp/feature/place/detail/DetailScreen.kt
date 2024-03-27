package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.feature.place.facilities.FacilitiesList
import co.nes.parceltestkmp.koin.KoinHelperKmp
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.ui.components.AspenImageCard
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.components.AspenTextButton
import co.nes.parceltestkmp.ui.components.AspenTopBar
import co.nes.parceltestkmp.ui.components.AspenTopBarLayout
import co.nes.parceltestkmp.ui.theme.AspenTheme.colors
import co.nes.parceltestkmp.ui.theme.AspenTheme.typography
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.heart_circle_activate
import parceltestkmp.composeapp.generated.resources.heart_circle_deactivate

@OptIn(ExperimentalResourceApi::class)
class DetailScreen : ComposableScreen({
    val viewModel: DetailsViewModelDemo = KoinHelperKmp.getViewModel()
    val placeInfo = viewModel.singlePlaceInfo.collectAsState().value
    val allPlaceInfo = viewModel.allPlaceInfo.collectAsState().value
    val placeInfoByName = viewModel.placeInfoByName.collectAsState().value

    val isFavorite = true

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colors.background
            ),
        topBar = {
            AspenTopBar(
                layout = AspenTopBarLayout.Detail
            )
        },
        bottomBar = {
            BottomBarDetail()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    top = 20.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
             val url = "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064"

            AspenImageCard(
                modifier = Modifier
                    .widthIn(min = 335.dp)
                    .height(340.dp)
                    .padding(bottom = 32.dp),
                modifierOut = Modifier
                    .widthIn(min = 335.dp)
                    .height(340.dp),
                imageUrl = url,
                contentDescription = "",
                contentOut = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(end = 34.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = if (isFavorite) painterResource(Res.drawable.heart_circle_activate)
                            else painterResource(Res.drawable.heart_circle_deactivate),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(44.dp),
                        )
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                AspenText(
                    text = "Coeurdes Alpes",
                    style = typography.titleMedium,
                )
                AspenTextButton(
                    text = "Show map",
                    style = typography.headlineMedium,
                    color = colors.primary,
                    onClick = {}
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = colors.startIconColor,
                    modifier = Modifier.size(16.dp),
                )
                AspenText(
                    text = "4.5 (355 Reviews)",
                    style = typography.bodySmall,
                    color = colors.onSecondary,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AspenText(
                    "Aspen is as close as one can get to a storybook\n" +
                            "alpine town in America. The choose-your-own-\n" +
                            "adventure possibilities—skiing, hiking, dining\n" +
                            "shopping and ....",
                    style = typography.bodyMedium,
                    color = colors.headLine,
                    modifier = Modifier.width(335.dp).height(80.dp),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AspenTextButton(
                    text = "Read more",
                    style = typography.headlineMedium,
                    color = colors.primary,
                    onClick = {}
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Read more",
                    tint = colors.primary,
                    modifier = Modifier.size(24.dp).padding(start = 4.dp),
                )
            }

            FacilitiesList()

            Spacer(modifier = Modifier.height(32.dp))

            AspenButton(onClick = {
                viewModel.getPlaceInfo()
            }) {
                AspenText("Get")
            }

            AspenText(placeInfo)

            AspenButton(onClick = {
                viewModel.getAllPlaceInfo()
            }) {
                AspenText("Get All")
            }

            AspenText(allPlaceInfo)

            AspenButton(onClick = {
                viewModel.getPlaceByName("Central Park")
            }) {
                AspenText("Get by Name")
            }
            AspenText(placeInfoByName)
        }
    }
})

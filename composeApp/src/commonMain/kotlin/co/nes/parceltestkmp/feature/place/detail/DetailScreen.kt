package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListCommand
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsCommand
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsIntent
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
import kotlinx.coroutines.flow.onEach
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.heart_circle_activate
import parceltestkmp.composeapp.generated.resources.heart_circle_deactivate

@OptIn(ExperimentalResourceApi::class)
internal class DetailScreen(
    private val homeViewModel: HomeViewModel) : DetailComposableScreen({
    val detailViewModel= it as PlaceDetailViewModel

    val navigator = LocalNavigator.currentOrThrow

    val detailViewModelDemo: DetailsViewModelDemo = KoinHelperKmp.getViewModel()
    val placeInfo = detailViewModelDemo.singlePlaceInfo.collectAsState().value
    val allPlaceInfo = detailViewModelDemo.allPlaceInfo.collectAsState().value
    val placeInfoByName = detailViewModelDemo.placeInfoByName.collectAsState().value
    val vacationInfoData = detailViewModelDemo.vacationInfoData.collectAsState().value

    val placeDetails = detailViewModel.viewState.collectAsState()


    detailViewModel.viewCommand.onEach {
        it.consume { command ->
            when (command) {
                PlaceDetailsCommand.GoBack -> {
                    navigator.pop()
                }

                PlaceDetailsCommand.FavoriteUpdated -> homeViewModel.updateSelectedPlace(
                    placeDetails.value.placeDetail.isFavorite
                )
            }
        }
    }.collectAsState(PlaceListCommand.None)

    LaunchedEffect(Unit) {
        if (placeDetails.value.placeDetail.name.isEmpty()) {
            homeViewModel.selectedPlace?.let {
                detailViewModel.onIntent(PlaceDetailsIntent.Screen.GetPlaceDetails(it))
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colors.background
            ),
        topBar = {
            AspenTopBar(
                layout = AspenTopBarLayout.Detail
            ) {
                detailViewModel.sendCommand(PlaceDetailsCommand.GoBack)
            }
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
            AspenImageCard(
                modifier = Modifier
                    .widthIn(min = 335.dp)
                    .height(340.dp)
                    .padding(bottom = 32.dp),
                modifierOut = Modifier
                    .widthIn(min = 335.dp)
                    .height(340.dp),
                imageUrl = homeViewModel.selectedPlace?.imageUrl.orEmpty(),
                contentDescription = "",
                contentOut = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth().padding(end = 34.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = if (placeDetails.value.placeDetail.isFavorite) painterResource(
                                Res.drawable.heart_circle_activate
                            )
                            else painterResource(Res.drawable.heart_circle_deactivate),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(44.dp)
                                .clickable {
                                    detailViewModel.onIntent(PlaceDetailsIntent.Screen.ChangeIsFavorite)
                                },
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
                    text = placeDetails.value.placeDetail.name,
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
                    text = "${placeDetails.value.placeDetail.rating} (${placeDetails.value.placeDetail.reviews} Reviews)",
                    style = typography.bodySmall,
                    color = colors.onSecondary,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AspenText(
                    placeDetails.value.placeDetail.description,
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

            FacilitiesList(placeDetails.value.placeDetail.facilities)

            if (placeDetails.value.placeDetail.facilities.isNotEmpty()) {
                Spacer(modifier = Modifier.height(50.dp))

                AspenButton(onClick = {
                    detailViewModelDemo.getPlaceInfo()
                }) {
                    AspenText("Get")
                }

                AspenText(placeInfo)

                AspenButton(onClick = {
                    detailViewModelDemo.getAllPlaceInfo()
                }) {
                    AspenText("Get All")
                }

                AspenText(allPlaceInfo)

                AspenButton(onClick = {
                    detailViewModelDemo.getPlaceByName("Central Park")
                }) {
                    AspenText("Get by Name")
                }
                AspenText(placeInfoByName)
                AspenText(placeInfoByName)

                AspenButton(onClick = {
                    detailViewModelDemo.getVacationInfoData()
                }) {
                    AspenText("Get all and save in db")
                }
                AspenText(vacationInfoData)
            }
        }
    }
})

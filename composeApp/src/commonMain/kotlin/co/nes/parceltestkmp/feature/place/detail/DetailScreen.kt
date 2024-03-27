package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.koin.KoinHelperKmp
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.components.AspenTopBar
import co.nes.parceltestkmp.ui.components.AspenTopBarLayout
import co.nes.parceltestkmp.ui.theme.AspenTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.AspenSplashScreen
import parceltestkmp.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
class DetailScreen : ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow
    val viewModel: DetailsViewModelDemo = KoinHelperKmp.getViewModel()
    val placeInfo = viewModel.singlePlaceInfo.collectAsState().value
    val allPlaceInfo = viewModel.allPlaceInfo.collectAsState().value
    val placeInfoByName = viewModel.placeInfoByName.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AspenTheme.colors.background
            ),
        topBar = {
            AspenTopBar(
                layout = AspenTopBarLayout.Detail
            )
        },
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Image(
                    painter = painterResource(Res.drawable.AspenSplashScreen),
                    contentDescription = "Splash Screen Aspen",
                    modifier = Modifier.width(335.dp).height(340.dp),
                    contentScale = ContentScale.FillBounds,
                )
            }
            AspenText("Detail Screen")

            AspenButton(onClick = {
                navigator.pop()
            }) {
                AspenText("Go to Home Screen")
            }

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

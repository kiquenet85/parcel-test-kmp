package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.koin.KoinHelperKmp

class DetailScreen : ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow
    val viewModel: DetailsViewModelDemo = KoinHelperKmp.getViewModel()
    val placeInfo = viewModel.singlePlaceInfo.collectAsState().value
    val allPlaceInfo = viewModel.allPlaceInfo.collectAsState().value
    val placeInfoByName = viewModel.placeInfoByName.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text("Detail Screen")

            Button(onClick = {
                navigator.pop()
            }) {
                Text("Go to Home Screen")
            }

            Button(onClick = {
                viewModel.getPlaceInfo()
            }) {
                Text("Get")
            }

            Text(placeInfo)

            Button(onClick = {
                viewModel.getAllPlaceInfo()
            }) {
                Text("Get All")
            }

            Text(allPlaceInfo)

            Button(onClick = {
                viewModel.getPlaceByName("Central Park")
            }) {
                Text("Get by Name")
            }
            Text(placeInfoByName)
        }
    }
})

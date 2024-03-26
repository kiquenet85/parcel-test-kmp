package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen

class DetailScreen: ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow

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
        }
    }
})

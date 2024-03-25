package co.nes.parceltestkmp.vacation.place.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.ui.ComposableScreen

class HomeScreen(): ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text("Home Screen")

            Button(onClick = {
                navigator.pop()
            }) {
                Text("Go to Splash Screen")
            }
        }
    }
})

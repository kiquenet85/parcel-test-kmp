package co.nes.parceltestkmp.ui

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import co.nes.parceltestkmp.App

fun MainViewController() = ComposeUIViewController {
    Navigator(App())
}

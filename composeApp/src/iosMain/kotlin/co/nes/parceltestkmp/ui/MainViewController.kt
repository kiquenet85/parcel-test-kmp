package co.nes.parceltestkmp.ui

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import co.nes.parceltestkmp.App
import co.nes.parceltestkmp.koin.KoinContainer

fun MainViewController() = ComposeUIViewController {
    KoinContainer.initKoin()
    Navigator(App())
}

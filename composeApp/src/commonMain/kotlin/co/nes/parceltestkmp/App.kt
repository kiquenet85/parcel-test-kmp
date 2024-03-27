package co.nes.parceltestkmp

import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.feature.splash_screen.SplashScreen
import co.nes.parceltestkmp.ui.theme.AspenTheme

class App: ComposableScreen({
    AspenTheme {
        Navigator(screen = SplashScreen()) { navigator ->
            FadeTransition(navigator = navigator)
        }
    }
})

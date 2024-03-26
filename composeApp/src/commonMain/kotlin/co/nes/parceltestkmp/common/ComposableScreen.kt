package co.nes.parceltestkmp.common

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

open class ComposableScreen(private val composable: @Composable () -> Unit): Screen {
    @Composable
    override fun Content() {
        composable()
    }
}

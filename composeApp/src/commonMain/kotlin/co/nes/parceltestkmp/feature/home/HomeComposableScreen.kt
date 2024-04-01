package co.nes.parceltestkmp.feature.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel

open class HomeComposableScreen(
    private val composable: @Composable (screenModel: ScreenModel) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeViewModel>()
        composable(screenModel)
    }
}

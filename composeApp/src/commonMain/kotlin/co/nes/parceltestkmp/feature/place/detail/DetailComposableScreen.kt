package co.nes.parceltestkmp.feature.place.detail

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailViewModel

open class DetailComposableScreen(
    private val composable: @Composable (screenModel: ScreenModel) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<PlaceDetailViewModel>()
        composable(screenModel)
    }
}

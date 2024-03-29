package co.nes.parceltestkmp.feature.home.mvi.place.factory

import co.nes.parceltestkmp.feature.home.mvi.place.action.GetVacationInfo
import co.nes.parceltestkmp.feature.home.mvi.place.action.UpdateSelectedPlace
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListIntent
import co.nes.parceltestkmp.providers.DispatcherProvider

internal class PlaceListActionFactory {
    fun fromIntent(
        intent: PlaceListIntent.Screen,
        viewModel: HomeViewModel,
        dispatcherProvider: DispatcherProvider
    ) = when (intent) {
        is PlaceListIntent.Screen.GetVacationInfo -> GetVacationInfo(
            viewModel = viewModel, dispatcherProvider = dispatcherProvider
        )

        PlaceListIntent.Screen.UpdateSelectedPlace -> UpdateSelectedPlace(
            viewModel = viewModel, dispatcherProvider = dispatcherProvider
        )
    }
}

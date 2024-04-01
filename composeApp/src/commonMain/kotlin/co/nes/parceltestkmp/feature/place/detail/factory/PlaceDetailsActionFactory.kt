package co.nes.parceltestkmp.feature.place.detail.factory

import co.nes.parceltestkmp.feature.place.detail.action.GetPlaceDetailAction
import co.nes.parceltestkmp.feature.place.detail.action.UpdatePlaceDetailFavoriteAction
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsIntent
import co.nes.parceltestkmp.providers.DispatcherProvider

internal class PlaceDetailsActionFactory {
    fun fromIntent(
        intent: PlaceDetailsIntent.Screen,
        dispatcherProvider: DispatcherProvider,
        viewModel: PlaceDetailViewModel,
    ) = when (intent) {
        is PlaceDetailsIntent.Screen.GetPlaceDetails -> GetPlaceDetailAction(
            dispatcherProvider = dispatcherProvider,
            viewModel = viewModel,
            placeUI = intent.placeUI
        )

        PlaceDetailsIntent.Screen.ChangeIsFavorite -> UpdatePlaceDetailFavoriteAction(
            viewModel = viewModel,
            dispatcherProvider = dispatcherProvider,
        )
    }
}

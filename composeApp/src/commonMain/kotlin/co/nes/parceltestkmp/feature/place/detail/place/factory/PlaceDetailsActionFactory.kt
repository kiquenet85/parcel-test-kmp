package co.nes.parceltestkmp.feature.place.detail.place.factory

import co.nes.parceltestkmp.feature.place.detail.place.action.GetPlaceDetailAction
import co.nes.parceltestkmp.feature.place.detail.place.action.UpdatePlaceDetailFavoriteAction
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailsIntent
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
            dispatcherProvider = dispatcherProvider
        )
    }
}

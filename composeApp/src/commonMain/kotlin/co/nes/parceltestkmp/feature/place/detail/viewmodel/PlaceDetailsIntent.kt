package co.nes.parceltestkmp.feature.place.detail.viewmodel

import co.nes.parceltestkmp.common.mvi.mvi.Intent
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.place.model.PlaceDetail

internal sealed class PlaceDetailsIntent : Intent<PlaceDetailsIntent> {
    internal sealed class Screen: PlaceDetailsIntent() {
        data class GetPlaceDetails(val placeUI: PlaceUI): Screen()
        data object ChangeIsFavorite: Screen()
    }

    internal sealed class Reduce: PlaceDetailsIntent() {
        data class UpdatePlaceDetails(val placeDetail: PlaceDetail): Reduce()
    }
}

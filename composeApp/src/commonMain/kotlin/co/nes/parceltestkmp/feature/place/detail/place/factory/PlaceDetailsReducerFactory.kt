package co.nes.parceltestkmp.feature.place.detail.place.factory

import co.nes.parceltestkmp.common.mvi.mvi.Reducer
import co.nes.parceltestkmp.feature.place.detail.place.reducer.UpdatePlaceFavoriteReducer
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailsIntent
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailsState

internal class PlaceDetailsReducerFactory {
    fun fromIntent(intent: PlaceDetailsIntent.Reduce): Reducer<PlaceDetailsState> =
        when (intent) {
            is PlaceDetailsIntent.Reduce.UpdatePlaceDetails -> UpdatePlaceFavoriteReducer(intent.placeDetail)
        }
}

package co.nes.parceltestkmp.feature.place.detail.factory

import co.nes.parceltestkmp.common.mvi.mvi.Reducer
import co.nes.parceltestkmp.feature.place.detail.reducer.UpdatePlaceFavoriteReducer
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsIntent
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsState

internal class PlaceDetailsReducerFactory {
    fun fromIntent(intent: PlaceDetailsIntent.Reduce): Reducer<PlaceDetailsState> =
        when (intent) {
            is PlaceDetailsIntent.Reduce.UpdatePlaceDetails -> UpdatePlaceFavoriteReducer(intent.placeDetail)
        }
}

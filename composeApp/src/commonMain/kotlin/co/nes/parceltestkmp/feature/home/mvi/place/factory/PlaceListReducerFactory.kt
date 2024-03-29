package co.nes.parceltestkmp.feature.home.mvi.place.factory

import co.nes.parceltestkmp.common.mvi.mvi.Reducer
import co.nes.parceltestkmp.feature.home.mvi.place.reducer.UpdatePlaceListReducer
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListIntent
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListViewSate

internal class PlaceListReducerFactory {
    fun fromIntent(intent: PlaceListIntent.Reduce): Reducer<PlaceListViewSate> =
        when (intent) {
            is PlaceListIntent.Reduce.UpdatePlaceListList -> UpdatePlaceListReducer(intent.popular, intent.recommended)
        }
}

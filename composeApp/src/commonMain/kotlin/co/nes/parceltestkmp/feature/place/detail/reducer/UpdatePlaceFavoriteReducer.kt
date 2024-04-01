package co.nes.parceltestkmp.feature.place.detail.reducer

import co.nes.parceltestkmp.common.mvi.mvi.Reducer
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsState
import co.nes.parceltestkmp.feature.place.model.PlaceDetail

internal class UpdatePlaceFavoriteReducer(
    private val placeDetail: PlaceDetail
): Reducer<PlaceDetailsState> {
    override suspend fun reduce(viewState: PlaceDetailsState) =
      viewState.copy( placeDetail = placeDetail )
}


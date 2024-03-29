package co.nes.parceltestkmp.feature.home.mvi.place.reducer

import co.nes.parceltestkmp.common.mvi.mvi.Reducer
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListViewSate

internal class UpdateSelectedPlaceOnListReducer(
    private val popular: List<PlaceUI>,
    private val recommended: List<PlaceUI>
) : Reducer<PlaceListViewSate> {
    override suspend fun reduce(viewState: PlaceListViewSate) =
        viewState.copy(popular = popular, recommended = recommended)
}


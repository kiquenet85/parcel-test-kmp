package co.nes.parceltestkmp.feature.home.mvi.place.viewmodel

import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI

internal data class PlaceListViewSate(
    val popular: List<PlaceUI> = emptyList(),
    val recommended: List<PlaceUI> = emptyList(),
)
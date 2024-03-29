package co.nes.parceltestkmp.ui.components

import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI

sealed class AspenHorizontalListValue(
    val name: String,
) {
    data class PlaceValue(val place: PlaceUI?) : AspenHorizontalListValue(place?.name ?: "")
}

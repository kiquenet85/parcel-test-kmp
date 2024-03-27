package co.nes.parceltestkmp.ui.components

import co.nes.parceltestkmp.feature.place.model.Place

sealed class AspenHorizontalListValue(
    val name: String,
) {
    data class PlaceValue(val place: Place?) : AspenHorizontalListValue(place?.name ?: "")
}

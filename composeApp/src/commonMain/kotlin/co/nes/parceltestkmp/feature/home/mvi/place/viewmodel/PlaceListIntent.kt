package co.nes.parceltestkmp.feature.home.mvi.place.viewmodel

import co.nes.parceltestkmp.common.mvi.mvi.Intent
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI

internal sealed class PlaceListIntent : Intent<PlaceListIntent> {
    internal sealed class Screen: PlaceListIntent() {
        data object GetVacationInfo: Screen()
        data object UpdateSelectedPlace: Screen()
    }

    internal sealed class Reduce: PlaceListIntent() {
        data class UpdatePlaceList(val popular: List<PlaceUI>, val recommended: List<PlaceUI>): Reduce()
        data class UpdateSelectedPlace(val popular: List<PlaceUI>, val recommended: List<PlaceUI>): Reduce()
    }
}

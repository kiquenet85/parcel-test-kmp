package co.nes.parceltestkmp.feature.home.mvi.place.viewmodel

internal sealed class PlaceListCommand {
    data object None : PlaceListCommand()
    data object SelectPlaceList : PlaceListCommand()
    data object ExitScreen : PlaceListCommand()
}
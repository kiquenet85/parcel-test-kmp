package co.nes.parceltestkmp.feature.place.detail.place.viewmodel

internal sealed class PlaceDetailsCommand {
    data object GoBack : PlaceDetailsCommand()
}
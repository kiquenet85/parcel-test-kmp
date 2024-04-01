package co.nes.parceltestkmp.feature.place.detail.viewmodel

internal sealed class PlaceDetailsCommand {
    data object GoBack : PlaceDetailsCommand()
    data object FavoriteUpdated : PlaceDetailsCommand()
}
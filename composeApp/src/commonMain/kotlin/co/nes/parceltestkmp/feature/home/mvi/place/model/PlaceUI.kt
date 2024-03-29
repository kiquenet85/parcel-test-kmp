package co.nes.parceltestkmp.feature.home.mvi.place.model

data class PlaceUI(
    val name : String,
    val rating : Double,
    val section : Int,
    val isFavorite : Boolean,
    val imageUrl : String
)

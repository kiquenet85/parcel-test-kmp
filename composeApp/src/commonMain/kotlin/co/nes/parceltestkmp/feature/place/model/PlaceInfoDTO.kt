package co.nes.parceltestkmp.feature.place.model

data class PlaceInfoDTO(
    val name: String,
    val rating: Double,
    val reviews: Int,
    val description: String,
    val facilities: List<String>,
    val price: Double,
    val latitude: Double,
    val longitude: Double
)
package co.nes.parceltestkmp.feature.place.model

import kotlinx.serialization.Serializable

@Serializable
data class VacationInfoDTO(
    val places: Category,
    val hotels: Category? = null,
    val food: Category? = null,
    val adventures: Category? = null
)

@Serializable
data class Category(
    val id: String? = null,
    val popular: List<Place>,
    val recommended: List<Place>
)

@Serializable
data class Place(
    val name: String,
    val image: String,
    val isFavorite: Boolean,
    val rating: Double
)

data class PlaceDetail(
    val name: String,
    val rating: Double,
    val reviews: Int,
    val description: String,
    val facilities: List<String>,
    val price: String,
    val latitude: Double,
    val longitude: Double
)

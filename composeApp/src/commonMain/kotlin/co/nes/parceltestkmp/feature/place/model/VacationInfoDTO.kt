package co.nes.parceltestkmp.feature.place.model

import co.nes.parceltestkmp.feature.place.facilities.Facility
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
    val imageUrl: String,
    val isFavorite: Boolean,
    val rating: Double
)

data class PlaceDetail(
    val name: String,
    val rating: Double,
    val reviews: Int,
    val description: String,
    val facilities: List<Facility>,
    val price: String,
    val latitude: Double,
    val longitude: Double,
    val isFavorite: Boolean
)

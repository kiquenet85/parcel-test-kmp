package co.nes.parceltestkmp.feature.place.model

data class VacationInfoDTO(
    val places: Category<Place>,
    val hotels: Category<Any?>? = null,
    val food: Category<Any?>? = null,
    val adventures: Category<Any?>? = null
)

data class Category<T>(
    val popular: List<T>,
    val recommended: List<T>
)

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
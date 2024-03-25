package vacation.place.model

class VacationInfoDTO {

    data class VacationInformationDTO(
        val locations: Category<Place>,
        val hotels: Category<Any?>,
        val food: Category<Any?>,
        val adventures: Category<Any?>
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
}
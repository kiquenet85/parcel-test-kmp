package co.nes.parceltestkmp.feature.place.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetailDTO(
    val name: String,
    @SerialName("score")
    val rating: Double,
    val reviews: Int,
    val description: String,
    val facilities: List<String>,
    val price: Double,
    val latitude: Double,
    val longitude: Double,
    val imageURL : String = String(),
    val isFavorite: Boolean = false
)

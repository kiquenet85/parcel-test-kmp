package co.nes.parceltestkmp.feature.place.facilities

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.bath_tub
import parceltestkmp.composeapp.generated.resources.food
import parceltestkmp.composeapp.generated.resources.pool
import parceltestkmp.composeapp.generated.resources.wifi

@OptIn(ExperimentalResourceApi::class)
sealed class Facility(
    val idx: Int, val name: String, val icon: DrawableResource
) {
    data object Restrooms : Facility(0, "Restrooms", Res.drawable.bath_tub)
    data object BikeRentals : Facility(1, "Bike Rentals", Res.drawable.food)
    data object FoodStands : Facility(2, "Food Stands", Res.drawable.pool)
    data object Playgrounds : Facility(3, "Playgrounds", Res.drawable.wifi)
    data object ViewingPoints : Facility(4, "Viewing Points", Res.drawable.bath_tub)
    data object Parking : Facility(5, "Parking", Res.drawable.food)
    data object GiftShop : Facility(6, "Gift Shop", Res.drawable.pool)
    data object ArtGalleries : Facility(7, "Art Galleries", Res.drawable.wifi)
    data object Cafes : Facility(8, "Cafes", Res.drawable.bath_tub)
    data object AudioGuides : Facility(9, "Audio Guides", Res.drawable.food)
    data object GuidedTours : Facility(10, "Guided Tours", Res.drawable.pool)
    data object Bookshop : Facility(11, "Bookshop", Res.drawable.wifi)
    data object Restaurants : Facility(12, "Restaurants", Res.drawable.food)
    data object ObservationDecks : Facility(13, "Observation Decks", Res.drawable.pool)
    data object HikingTrails : Facility(14, "Hiking Trails", Res.drawable.bath_tub)
    data object VisitorCenter : Facility(15, "Visitor Center", Res.drawable.wifi)
    data object Museum : Facility(16, "Museum", Res.drawable.food)
    data object Gardens : Facility(17, "Gardens", Res.drawable.wifi)
    data object CampingSites : Facility(18, "Camping Sites", Res.drawable.pool)
    data object Heater : Facility(19, "1 Heater", Res.drawable.bath_tub)
    data object Dinner : Facility(20, "Dinner", Res.drawable.food)
    data object Tub : Facility(21, "1 Tub", Res.drawable.pool)
    data object Pool : Facility(22, "Pool", Res.drawable.wifi)

    companion object {
        private val allFacilities = listOf(
            Restrooms, BikeRentals, FoodStands, Playgrounds, ViewingPoints, Parking,
            GiftShop, ArtGalleries, Cafes, AudioGuides, GuidedTours, Bookshop,
            Restaurants, ObservationDecks, HikingTrails, VisitorCenter, Museum,
            Gardens, CampingSites, Heater, Dinner, Tub, Pool
        )
        fun getFacility(value : String) : Facility? {
            return allFacilities.firstOrNull { it.name == value }
        }
    }
}

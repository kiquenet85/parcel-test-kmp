package co.nes.parceltestkmp.feature.place.data.remote

import co.nes.parceltestkmp.feature.place.model.PlaceDetailDTO
import co.nes.parceltestkmp.feature.place.model.VacationInfoDTO
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

sealed class PlaceDetail(val id: String) {
    data object CentralPark : PlaceDetail("Central Park")
    data object GoldenGateBridge : PlaceDetail("Golden Gate Bridge")
    data object TheLouvre : PlaceDetail("The Louvre")
    data object Colosseum : PlaceDetail("Colosseum")
    data object EiffelTower : PlaceDetail("Eiffel Tower")
    data object GreatWallOfChina : PlaceDetail("Great Wall of China")
    data object MachuPicchu : PlaceDetail("Machu Picchu")
    data object StatueOfLiberty : PlaceDetail("Statue of Liberty")
    data object TajMahal : PlaceDetail("Taj Mahal")
    data object GrandCanyon : PlaceDetail("Grand Canyon")
}

interface PlaceRemoteSource {
    suspend fun fetchPlaceDetailPlace(placeID: String): Flow<PlaceDetailDTO?>
    suspend fun fetchVacationInfo(): Flow<VacationInfoDTO>
}

class PlaceRemoteImpl(
    private val dispatcherProvider: DispatcherProvider
) : PlaceRemoteSource {

    override suspend fun fetchPlaceDetailPlace(placeID: String): Flow<PlaceDetailDTO?> =
        withContext(dispatcherProvider.getIO()) {
            val mockedJson = when (placeID) {
                PlaceDetail.CentralPark.id -> {
                    """
                        {
                          "name": "Central Park",
                          "score": 4.8,
                          "reviews": 4000,
                          "description": "A verdant oasis in the heart of New York City, offering serene landscapes and outdoor activities.",
                          "facilities": ["Restrooms", "Bike Rentals", "Food Stands", "Playgrounds"],
                          "price": 0.1,
                          "latitude": 40.785091,
                          "longitude": -73.968285
                        }
                    """.trimIndent()
                }

                PlaceDetail.GoldenGateBridge.id -> {
                    """
                        {
                          "name": "Golden Gate Bridge",
                          "score": 4.7,
                          "reviews": 2890,
                          "description": "Iconic suspension bridge offering panoramic views of San Francisco Bay and the Pacific Ocean.",
                          "facilities": ["Viewing Points", "Parking", "Gift Shop"],
                          "price": 15.0,
                          "latitude": 37.8199286,
                          "longitude": -122.4782551
                        }
                     """.trimIndent()
                }

                PlaceDetail.TheLouvre.id -> {
                    """
                        {
                          "name": "The Louvre",
                          "score": 4.8,
                          "reviews": 10200,
                          "description": "World's largest art museum and a historic monument in Paris, home to thousands of works, including the Mona Lisa.",
                          "facilities": ["Art Galleries", "Cafes", "Gift Shop"],
                          "price": 30.0,
                          "latitude": 48.8606111,
                          "longitude": 2.337644
                        }
                    """.trimIndent()
                }

                PlaceDetail.Colosseum.id -> {
                    """
                        {
                          "name": "Colosseum",
                          "score": 4.9,
                          "reviews": 21500,
                          "description": "An ancient Roman amphitheater in Rome, known for its grandeur and historical significance.",
                          "facilities": ["Audio Guides", "Guided Tours", "Bookshop"],
                          "price": 12.0,
                          "latitude": 41.8902102,
                          "longitude": 12.4922309
                        }
                    """.trimIndent()
                }

                PlaceDetail.EiffelTower.id -> {
                    """
                        {
                          "name": "Eiffel Tower",
                          "score": 4.9,
                          "reviews": 19800,
                          "description": "Iconic French landmark offering city views from its sky-high observation decks.",
                          "facilities": ["Restaurants", "Gift Shops", "Observation Decks"],
                          "price": 25.0,
                          "latitude": 48.8588443,
                          "longitude": 2.2943506
                        }
                    """.trimIndent()
                }

                PlaceDetail.GreatWallOfChina.id -> {
                    """
                        {
                          "name": "Great Wall of China",
                          "score": 4.7,
                          "reviews": 8800,
                          "description": "Ancient series of walls and fortifications, stretching across northern China, built to protect against invasions.",
                          "facilities": ["Hiking Trails", "Guided Tours", "Visitor Center"],
                          "price": 20.0,
                          "latitude": 40.4319077,
                          "longitude": 116.5703749
                        }
                    """.trimIndent()
                }

                PlaceDetail.MachuPicchu.id -> {
                    """
                        {
                          "name": "Machu Picchu",
                          "score": 4.9,
                          "reviews": 5400,
                          "description": "15th-century Inca citadel situated on a mountain ridge in Peru, renowned for its archaeological significance.",
                          "facilities": ["Guided Tours", "Viewing Platforms", "Museum"],
                          "price": 70.0,
                          "latitude": -13.1631412,
                          "longitude": -72.5449629
                        }
                    """.trimIndent()
                }

                PlaceDetail.StatueOfLiberty.id -> {
                    """
                        {
                          "name": "Statue of Liberty",
                          "score": 4.6,
                          "reviews": 18000,
                          "description": "Iconic symbol of freedom and democracy, located on Liberty Island in New York Harbor.",
                          "facilities": ["Museum", "Gift Shop", "Observation Deck"],
                          "price": 18.0,
                          "latitude": 40.6892494,
                          "longitude": -74.0445004
                        }
                    """.trimIndent()
                }

                PlaceDetail.TajMahal.id -> {
                    """
                        {
                          "name": "Taj Mahal",
                          "score": 4.9,
                          "reviews": 9500,
                          "description": "Ivory-white marble mausoleum in the Indian city of Agra, a UNESCO World Heritage Site and symbol of love.",
                          "facilities": ["Guided Tours", "Gardens", "Visitor Center"],
                          "price": 15.0,
                          "latitude": 27.1751448,
                          "longitude": 78.0421422
                        }
                    """.trimIndent()
                }

                PlaceDetail.GrandCanyon.id -> {
                    """
                        {
                          "name": "Grand Canyon",
                          "score": 4.8,
                          "reviews": 4670,
                          "description": "Iconic natural wonder in the United States, known for its immense size and intricate and colorful landscape.",
                          "facilities": ["Hiking Trails", "Visitor Center", "Camping Sites"],
                          "price": 35.0,
                          "latitude": 36.1069652,
                          "longitude": -112.1129972
                        }
                    """.trimIndent()
                }

                else -> {
                    return@withContext flowOf(null)
                }
            }
            val placeDetail = Json.decodeFromString<PlaceDetailDTO>(mockedJson)

            delay(DELAY_TIME_NETWORK)

            flowOf(placeDetail)
        }

    override suspend fun fetchVacationInfo(): Flow<VacationInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            val jsonResponse = """
       {
          "places": {
            "popular": [
              {"name": "Central Park", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.8},
              {"name": "Golden Gate Bridge", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.7},
              {"name": "Times Square", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.5},
              {"name": "Big Ben", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.6},
              {"name": "Great Wall of China", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.9}
            ],
            "recommended": [
              {"name": "Eiffel Tower", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.8},
              {"name": "Colosseum", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.7},
              {"name": "Louvre Museum", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.6},
              {"name": "Sydney Opera House", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.5},
              {"name": "Statue of Liberty", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.7}
            ]
          },
          "hotels": {
            "popular": [
              {"name": "The Plaza", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.8},
              {"name": "The Ritz London", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.7},
              {"name": "Marina Bay Sands", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.9},
              {"name": "Burj Al Arab", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 5.0},
              {"name": "The Bellagio", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.8}
            ],
            "recommended": [
              {"name": "Waldorf Astoria", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.6},
              {"name": "The Peninsula", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.7},
              {"name": "Four Seasons", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.8},
              {"name": "Mandarin Oriental", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": true, "rating": 4.9},
              {"name": "Raffles Singapore", "imageUrl": "https://www.aspensnowmass.com/-/media/aspen-snowmass/images/image-cta-with-title-and-description/image-cta-card-tall/winter/2021-22/destination-overview-snow-groom-report-image-cta-tall-07282021/sheer-bliss.jpg?mw=564&mh=620&hash=C2F5471694BAC911CE2E9D53EF9AC064", "isFavorite": false, "rating": 4.7}
            ]
          },
          "food": {
            "popular": [],
            "recommended": []
          },
          "adventures": {
            "popular": [],
            "recommended": []
          }
        }
                """.trimIndent()

            val vacationInfoDTO = Json.decodeFromString<VacationInfoDTO>(jsonResponse)

            flowOf(vacationInfoDTO)
        }


    companion object {
        const val DELAY_TIME_NETWORK = 500L
    }
}

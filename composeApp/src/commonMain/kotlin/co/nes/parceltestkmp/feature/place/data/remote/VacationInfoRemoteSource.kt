package co.nes.parceltestkmp.feature.place.data.remote

import co.nes.parceltestkmp.feature.place.model.Category
import co.nes.parceltestkmp.feature.place.model.Place
import co.nes.parceltestkmp.providers.DispatcherProvider
import co.nes.parceltestkmp.feature.place.model.VacationInfoDTO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

interface VacationInfoRemoteSource {
    suspend fun getVacationInfo() :  VacationInfoDTO
}

class VacationInfoRemoteImpl(
    private val dispatcherProvider: DispatcherProvider
) : VacationInfoRemoteSource {
    override suspend fun getVacationInfo(): VacationInfoDTO = withContext(dispatcherProvider.getIO()) {
        val jsonResponse = """
       {
          "places": {
            "popular": [
              {"name": "Central Park", "image": "https://example.com/images/central_park.jpg", "isFavorite": true, "rating": 4.8},
              {"name": "Golden Gate Bridge", "image": "https://example.com/images/golden_gate_bridge.jpg", "isFavorite": false, "rating": 4.7},
              {"name": "Times Square", "image": "https://example.com/images/times_square.jpg", "isFavorite": true, "rating": 4.5},
              {"name": "Big Ben", "image": "https://example.com/images/big_ben.jpg", "isFavorite": false, "rating": 4.6},
              {"name": "Great Wall of China", "image": "https://example.com/images/great_wall_of_china.jpg", "isFavorite": true, "rating": 4.9}
            ],
            "recommended": [
              {"name": "Eiffel Tower", "image": "https://example.com/images/eiffel_tower.jpg", "isFavorite": false, "rating": 4.8},
              {"name": "Colosseum", "image": "https://example.com/images/colosseum.jpg", "isFavorite": true, "rating": 4.7},
              {"name": "Louvre Museum", "image": "https://example.com/images/louvre_museum.jpg", "isFavorite": false, "rating": 4.6},
              {"name": "Sydney Opera House", "image": "https://example.com/images/sydney_opera_house.jpg", "isFavorite": true, "rating": 4.5},
              {"name": "Statue of Liberty", "image": "https://example.com/images/statue_of_liberty.jpg", "isFavorite": false, "rating": 4.7}
            ]
          },
          "hotels": {
            "popular": [
              {"name": "The Plaza", "image": "https://example.com/images/the_plaza.jpg", "isFavorite": true, "rating": 4.8},
              {"name": "The Ritz London", "image": "https://example.com/images/the_ritz_london.jpg", "isFavorite": false, "rating": 4.7},
              {"name": "Marina Bay Sands", "image": "https://example.com/images/marina_bay_sands.jpg", "isFavorite": true, "rating": 4.9},
              {"name": "Burj Al Arab", "image": "https://example.com/images/burj_al_arab.jpg", "isFavorite": false, "rating": 5.0},
              {"name": "The Bellagio", "image": "https://example.com/images/the_bellagio.jpg", "isFavorite": true, "rating": 4.8}
            ],
            "recommended": [
              {"name": "Waldorf Astoria", "image": "https://example.com/images/waldorf_astoria.jpg", "isFavorite": false, "rating": 4.6},
              {"name": "The Peninsula", "image": "https://example.com/images/the_peninsula.jpg", "isFavorite": true, "rating": 4.7},
              {"name": "Four Seasons", "image": "https://example.com/images/four_seasons.jpg", "isFavorite": false, "rating": 4.8},
              {"name": "Mandarin Oriental", "image": "https://example.com/images/mandarin_oriental.jpg", "isFavorite": true, "rating": 4.9},
              {"name": "Raffles Singapore", "image": "https://example.com/images/raffles_singapore.jpg", "isFavorite": false, "rating": 4.7}
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

        vacationInfoDTO
    }


    companion object {
        val  tempData = VacationInfoDTO(
            places = Category(
                popular = listOf(
                    Place(
                        name = "Waldorf Astoria",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria1",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria2",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria3",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria4",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria5",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                    Place(
                        name = "Waldorf Astoria6",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = false,
                        rating = 4.6,
                    ),
                ),
                recommended = listOf(
                    Place(
                        name = "Recommended Waldorf Astoria",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                    Place(
                        name = "Recommended Waldorf Astoria1",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                    Place(
                        name = "Recommended Waldorf Astoria2",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                    Place(
                        name = "Recommended Waldorf Astoria3",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                    Place(
                        name = "Recommended Waldorf Astoria4",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                    Place(
                        name = "Recommended Waldorf Astoria5",
                        image = "https://example.com/images/waldorf_astoria.jpg",
                        isFavorite = true,
                        rating = 4.1,
                    ),
                )
            ),
        )
    }
}

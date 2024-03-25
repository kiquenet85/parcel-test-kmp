package co.nes.parceltestkmp.vacation.place.data.remote

import co.nes.parceltestkmp.providers.DispatcherProvider
import co.nes.parceltestkmp.vacation.place.model.VacationInfoDTO
import kotlinx.coroutines.withContext

interface VacationInfoRemoteSource {
    suspend fun getVacationInfo() :  VacationInfoDTO
}

class VacationInfoRemoteImpl(
    private val dispatcherProvider: DispatcherProvider
) : VacationInfoRemoteSource {
    override suspend fun getVacationInfo(): VacationInfoDTO = withContext(dispatcherProvider.getIO()) {
        val jsonResponse = """
            [
          {
            "name": "Central Park",
            "score": 4.8,
            "reviews": 3214,
            "description": "A verdant oasis in the heart of New York City, offering serene landscapes and outdoor activities.",
            "facilities": ["Restrooms", "Bike Rentals", "Food Stands", "Playgrounds"],
            "price": 0,
            "latitude": 40.785091,
            "longitude": -73.968285
          },
          {
            "name": "Golden Gate Bridge",
            "score": 4.7,
            "reviews": 2890,
            "description": "Iconic suspension bridge offering panoramic views of San Francisco Bay and the Pacific Ocean.",
            "facilities": ["Viewing Points", "Parking", "Gift Shop"],
            "price": 15,
            "latitude": 37.8199286,
            "longitude": -122.4782551
          },
          {
            "name": "The Louvre",
            "score": 4.8,
            "reviews": 10200,
            "description": "World's largest art museum and a historic monument in Paris, home to thousands of works, including the Mona Lisa.",
            "facilities": ["Art Galleries", "Cafes", "Gift Shop"],
            "price": 30,
            "latitude": 48.8606111,
            "longitude": 2.337644
          },
          {
            "name": "Colosseum",
            "score": 4.9,
            "reviews": 21500,
            "description": "An ancient Roman amphitheater in Rome, known for its grandeur and historical significance.",
            "facilities": ["Audio Guides", "Guided Tours", "Bookshop"],
            "price": 12,
            "latitude": 41.8902102,
            "longitude": 12.4922309
          },
          {
            "name": "Eiffel Tower",
            "score": 4.9,
            "reviews": 19800,
            "description": "Iconic French landmark offering city views from its sky-high observation decks.",
            "facilities": ["Restaurants", "Gift Shops", "Observation Decks"],
            "price": 25,
            "latitude": 48.8588443,
            "longitude": 2.2943506
          },
          {
            "name": "Great Wall of China",
            "score": 4.7,
            "reviews": 8800,
            "description": "Ancient series of walls and fortifications, stretching across northern China, built to protect against invasions.",
            "facilities": ["Hiking Trails", "Guided Tours", "Visitor Center"],
            "price": 20,
            "latitude": 40.4319077,
            "longitude": 116.5703749
          },
          {
            "name": "Machu Picchu",
            "score": 4.9,
            "reviews": 5400,
            "description": "15th-century Inca citadel situated on a mountain ridge in Peru, renowned for its archaeological significance.",
            "facilities": ["Guided Tours", "Viewing Platforms", "Museum"],
            "price": 70,
            "latitude": -13.1631412,
            "longitude": -72.5449629
          },
          {
            "name": "Statue of Liberty",
            "score": 4.6,
            "reviews": 18000,
            "description": "Iconic symbol of freedom and democracy, located on Liberty Island in New York Harbor.",
            "facilities": ["Museum", "Gift Shop", "Observation Deck"],
            "price": 18,
            "latitude": 40.6892494,
            "longitude": -74.0445004
          },
          {
            "name": "Taj Mahal",
            "score": 4.9,
            "reviews": 9500,
            "description": "Ivory-white marble mausoleum in the Indian city of Agra, a UNESCO World Heritage Site and symbol of love.",
            "facilities": ["Guided Tours", "Gardens", "Visitor Center"],
            "price": 15,
            "latitude": 27.1751448,
            "longitude": 78.0421422
          },
          {
            "name": "Grand Canyon",
            "score": 4.8,
            "reviews": 4670,
            "description": "Iconic natural wonder in the United States, known for its immense size and intricate and colorful landscape.",
            "facilities": ["Hiking Trails", "Guid
                    
                """.trimIndent()

        VacationInfoDTO()
    }

}


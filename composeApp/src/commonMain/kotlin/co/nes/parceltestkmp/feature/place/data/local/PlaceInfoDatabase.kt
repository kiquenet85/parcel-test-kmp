package co.nes.parceltestkmp.feature.place.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import co.nes.parceltestkmp.data_access.database.VacationDatabase
import co.nes.parceltestkmp.feature.place.model.PlaceInfoDTO
import co.nes.parceltestkmp.feature.place.model.toPlaceInfoDto
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface PlaceInfoDatabase {
    suspend fun getPlaceInfo(name: String): Flow<PlaceInfoDTO>
    suspend fun getAllPlacesInfo(): Flow<List<PlaceInfoDTO>>
    fun savePlaceInfo(placeInfo: PlaceInfoDTO)
    fun updatePlaceInfo(placeInfo: PlaceInfoDTO)
    fun deletePlaceInfo()
}

class PlaceInfoDatabaseImpl(
    private val database: VacationDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : PlaceInfoDatabase {

    override suspend fun getPlaceInfo(name: String): Flow<PlaceInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            database.placeInfoQueries.getPlaceInfoByName(name)
                .asFlow()
                .mapToOne(this.coroutineContext)
                .map { it.toPlaceInfoDto() }
        }

    override suspend fun getAllPlacesInfo(): Flow<List<PlaceInfoDTO>> =
        withContext(dispatcherProvider.getIO()) {
            database.placeInfoQueries.getPlaces()
                .asFlow()
                .mapToList(this.coroutineContext)
                .map { listPlaces -> listPlaces.map { it.toPlaceInfoDto() } }
        }

    override fun savePlaceInfo(placeInfo: PlaceInfoDTO) {
        database.placeInfoQueries.insertPlaceInfo(
            name = placeInfo.name,
            reviews = placeInfo.reviews.toLong(),
            score = placeInfo.reviews.toDouble(),
            description = placeInfo.description,
            facilities = placeInfo.facilities.joinToString(","),
            price = placeInfo.price,
            latitude = placeInfo.latitude,
            longitude = placeInfo.longitude,
        )
    }

    override fun updatePlaceInfo(placeInfo: PlaceInfoDTO) {
        TODO("Not yet implemented")
    }

    override fun deletePlaceInfo() {
        TODO("Not yet implemented")
    }

}

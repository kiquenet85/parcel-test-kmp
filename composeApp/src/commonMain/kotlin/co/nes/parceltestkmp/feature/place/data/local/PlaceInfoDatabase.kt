package co.nes.parceltestkmp.feature.place.data.local

import app.cash.sqldelight.coroutines.asFlow
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
    suspend fun savePlaceInfo(placeInfo: PlaceInfoDTO)
    suspend fun updatePlaceInfo(placeInfo: PlaceInfoDTO)
    suspend fun deletePlaceInfo(name: String)
}

class PlaceInfoDatabaseImpl(
    private val database: VacationDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : PlaceInfoDatabase {

    override suspend fun getPlaceInfo(name: String): Flow<PlaceInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            database.placeInfoQueries.getPlaceInfoByName(name)
                .asFlow()
                .map { placeInfo ->
                    placeInfo.executeAsOne().toPlaceInfoDto()
                }
        }

    override suspend fun getAllPlacesInfo(): Flow<List<PlaceInfoDTO>> =
        withContext(dispatcherProvider.getIO()) {
            database.placeInfoQueries.getPlaces()
                .asFlow()
                .map { listPlaces ->
                    listPlaces.executeAsList().map { singlePlace ->
                        singlePlace.toPlaceInfoDto()
                    }
                }
        }

    override suspend fun savePlaceInfo(placeInfo: PlaceInfoDTO) {
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

    override suspend fun updatePlaceInfo(placeInfo: PlaceInfoDTO) {
        database.placeInfoQueries.updatePlaceInfoByName(
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

    override suspend fun deletePlaceInfo(name: String) {
        database.placeInfoQueries.deletePlaceInfoByName(name)
    }
}

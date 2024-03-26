package co.nes.parceltestkmp.feature.place.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import co.nes.parceltestkmp.data_access.database.VacationDatabase
import co.nes.parceltestkmp.feature.place.model.PlaceInfoDTO
import co.nes.parceltestkmp.feature.place.model.toPlaceInfoDto
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface PlaceInfoDatabase {
    suspend fun getVacationInfo(name: String): Flow<PlaceInfoDTO>
    fun saveVacationInfo(placeInfo: PlaceInfoDTO)
    fun deleteVacationInfo()
}

class PlaceInfoDatabaseImpl(
    private val database: VacationDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : PlaceInfoDatabase {

    override suspend fun getVacationInfo(name: String): Flow<PlaceInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            database.placeInfoQueries.getLocationByName(name)
                .asFlow()
                .mapToOne(this.coroutineContext)
                .map { it.toPlaceInfoDto() }
        }

    override fun saveVacationInfo(placeInfo: PlaceInfoDTO) {
        database.placeInfoQueries.insertLocation(
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

    override fun deleteVacationInfo() {
        TODO("Not yet implemented")
    }

}

package co.nes.parceltestkmp.feature.place.data

import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabase
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteSource
import co.nes.parceltestkmp.feature.place.model.PlaceInfoDTO
import co.nes.parceltestkmp.feature.place.model.VacationInfoDTO
import co.nes.parceltestkmp.feature.place.model.toPlaceEntity
import co.nes.parceltestkmp.providers.DispatcherProvider
import conesparceltestkmp.PlaceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface PlaceInfoRepository {
    suspend fun fetchVacationInfo() : Flow<VacationInfoDTO>
    suspend fun fetchLocationPlaceInfo(locationId: String): Flow<PlaceInfoDTO>
    suspend fun getAllPlaceInfo(): Flow<List<PlaceEntity>>
    suspend fun getPlaceByName(name: String): Flow<PlaceEntity>
    suspend fun updatePlaceInfo(newItem: PlaceEntity)
}

class PlaceInfoRepositoryImpl(
    private val placeService: PlaceRemoteSource,
    private val placeDatabase: PlaceDatabase,
    private val dispatcherProvider: DispatcherProvider
) : PlaceInfoRepository {
    override suspend fun fetchVacationInfo(): Flow<VacationInfoDTO> = withContext(dispatcherProvider.getIO()) {
        placeService.fetchVacationInfo().map { vacationInfo ->
            val popularSection = vacationInfo.places.popular.map { it.toPlaceEntity(POPULAR_SECTION) }
            popularSection.forEach {
              placeDatabase.savePlaceEntity(it)
            }
            val recommendedSection = vacationInfo.places.recommended.map { it.toPlaceEntity(RECOMMENDED_SECTION) }
            recommendedSection.forEach {
                placeDatabase.savePlaceEntity(it)
            }
            vacationInfo
        }
    }

    override suspend fun fetchLocationPlaceInfo(locationId: String): Flow<PlaceInfoDTO> = withContext(dispatcherProvider.getIO()) {
        placeService.fetchPlaceDetailPlace(locationId).map { placeInfoRemote ->
            val currentItem = placeDatabase.getPlaceEntity(placeInfoRemote.name).firstOrNull()
            placeDatabase.updatePlaceEntity(placeInfoRemote.toPlaceEntity(currentItem?.section ?: 0L))
            placeInfoRemote
        }
    }

    override suspend fun getAllPlaceInfo(): Flow<List<PlaceEntity>> = withContext(dispatcherProvider.getIO()) {
        placeDatabase.getAllPlaces()
    }

    override suspend fun getPlaceByName(name: String): Flow<PlaceEntity> = withContext(dispatcherProvider.getIO()) {
        placeDatabase.getPlaceEntity(name)
    }

    override suspend fun updatePlaceInfo(newItem: PlaceEntity): Unit =
        withContext(dispatcherProvider.getIO()) {
            placeDatabase.updatePlaceEntity(newItem)
        }

    companion object {
        const val POPULAR_SECTION = 0
        const val RECOMMENDED_SECTION = 1
    }
}

package co.nes.parceltestkmp.feature.place.data

import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabase
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteSource
import co.nes.parceltestkmp.feature.place.model.PlaceDetailDTO
import co.nes.parceltestkmp.feature.place.model.VacationInfoDTO
import co.nes.parceltestkmp.feature.place.model.toPlaceEntity
import co.nes.parceltestkmp.providers.DispatcherProvider
import conesparceltestkmp.PlaceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime

interface PlaceInfoRepository {
    suspend fun fetchVacationInfo(): Flow<VacationInfoDTO>
    suspend fun fetchPlaceDetailsInfo(locationId: String): Flow<PlaceDetailDTO?>
    suspend fun getAllPlaceInfo(): Flow<List<PlaceEntity>>
    suspend fun getPlaceByName(name: String): Flow<PlaceEntity?>
    suspend fun updatePlaceInfo(newItem: PlaceEntity)

    @ExperimentalTime
    suspend fun getVacationInfoData(): Flow<VacationInfoDTO>
}

class PlaceInfoRepositoryImpl(
    private val placeService: PlaceRemoteSource,
    private val placeDatabase: PlaceDatabase,
    private val dispatcherProvider: DispatcherProvider
) : PlaceInfoRepository {
    private var cachedData: Flow<VacationInfoDTO>? = null
    private var cacheExpiryTime: Duration =
        Duration.ZERO // Initial value, assuming cache is initially empty

    @ExperimentalTime
    override suspend fun getVacationInfoData(): Flow<VacationInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            // Check if cache is populated and not expired
            if (cachedData != null && cacheExpiryTime > Duration.ZERO) {
                return@withContext cachedData!! // Return cached data
            }

            // If cache is empty or expired, fetch fresh data
            val freshData = fetchVacationInfo()

            // Update cache
            cachedData = freshData
            cacheExpiryTime = 1.minutes // 1 minute expiry time

            freshData
        }

    override suspend fun fetchVacationInfo(): Flow<VacationInfoDTO> =
        withContext(dispatcherProvider.getIO()) {
            placeService.fetchVacationInfo().map { vacationInfo ->
                val popularSection =
                    vacationInfo.places.popular.map { it.toPlaceEntity(POPULAR_SECTION) }
                popularSection.forEach {
                    placeDatabase.savePlaceEntity(it)
                }
                val recommendedSection =
                    vacationInfo.places.recommended.map { it.toPlaceEntity(RECOMMENDED_SECTION) }
                recommendedSection.forEach {
                    placeDatabase.savePlaceEntity(it)
                }
                vacationInfo
            }
        }

    override suspend fun fetchPlaceDetailsInfo(locationId: String): Flow<PlaceDetailDTO?> =
        withContext(dispatcherProvider.getIO()) {
            placeService.fetchPlaceDetailPlace(locationId)
                .catch { emit(null) }
                .map { placeInfoRemote ->
                    placeInfoRemote?.let {
                        val currentItem = placeDatabase.getPlaceEntity(placeInfoRemote.name)
                            .firstOrNull()
                        currentItem?.let {
                            placeDatabase.updatePlaceEntity(placeInfoRemote.toPlaceEntity(it.section))
                        } ?: run {
                            placeDatabase.savePlaceEntity(placeInfoRemote.toPlaceEntity(-1))
                        }
                    }
                    placeInfoRemote
                }
        }

    override suspend fun getAllPlaceInfo(): Flow<List<PlaceEntity>> =
        withContext(dispatcherProvider.getIO()) {
            placeDatabase.getAllPlaces()
        }

    override suspend fun getPlaceByName(name: String): Flow<PlaceEntity?> =
        withContext(dispatcherProvider.getIO()) {
            placeDatabase.getPlaceEntity(name)
                .catch { emit(null) }
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

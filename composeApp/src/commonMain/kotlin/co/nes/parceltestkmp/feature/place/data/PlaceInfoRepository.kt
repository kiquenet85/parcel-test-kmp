package co.nes.parceltestkmp.feature.place.data

import co.nes.parceltestkmp.feature.place.data.local.PlaceInfoDatabase
import co.nes.parceltestkmp.feature.place.data.remote.PlaceDetailRemoteSource
import co.nes.parceltestkmp.feature.place.model.PlaceInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PlaceInfoRepository {
    suspend fun getLocationPlaceInfo(locationId: String): Flow<PlaceInfoDTO>
    suspend fun getAllPlaceInfo(): Flow<List<PlaceInfoDTO>>
    suspend fun getPlaceByName(name: String): Flow<PlaceInfoDTO>

}

class PlaceInfoRepositoryImpl(
    private val placeInfoService: PlaceDetailRemoteSource,
    private val placeInfoDatabase: PlaceInfoDatabase
) : PlaceInfoRepository {

    override suspend fun getLocationPlaceInfo(locationId: String): Flow<PlaceInfoDTO> {
        return placeInfoService.getPlaceDetailPlace(locationId).map { placeInfoRemote ->
            placeInfoDatabase.updatePlaceInfo(placeInfoRemote)
            placeInfoRemote
        }
    }

    override suspend fun getAllPlaceInfo(): Flow<List<PlaceInfoDTO>> {
        return placeInfoDatabase.getAllPlacesInfo()
    }

    override suspend fun getPlaceByName(name: String): Flow<PlaceInfoDTO> {
        return placeInfoDatabase.getPlaceInfo(name)
    }
}

package co.nes.parceltestkmp.feature.place.data

import co.nes.parceltestkmp.feature.place.data.local.PlaceInfoDatabase
import co.nes.parceltestkmp.feature.place.data.remote.PlaceDetailRemoteSource
import co.nes.parceltestkmp.feature.place.model.PlaceInfoDTO
import kotlinx.coroutines.flow.Flow

interface PlaceInfoRepository {
    suspend fun getLocationDetails(locationId: String): Flow<PlaceInfoDTO>
    suspend fun getVacationInformation(): PlaceInfoDTO

}

class PlaceInfoRepositoryImpl(
    private val placeInfoService: PlaceDetailRemoteSource,
    private val placeInfoDatabase: PlaceInfoDatabase
): PlaceInfoRepository {
    override suspend fun getLocationDetails(locationId: String): Flow<PlaceInfoDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getVacationInformation(): PlaceInfoDTO {
        TODO("Not yet implemented")
    }


}

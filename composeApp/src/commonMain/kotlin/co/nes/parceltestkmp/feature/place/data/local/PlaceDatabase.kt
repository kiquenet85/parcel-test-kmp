package co.nes.parceltestkmp.feature.place.data.local

import app.cash.sqldelight.coroutines.asFlow
import co.nes.parceltestkmp.data_access.database.VacationDatabase
import co.nes.parceltestkmp.providers.DispatcherProvider
import conesparceltestkmp.PlaceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface PlaceDatabase {
    suspend fun getPlaceEntity(name: String): Flow<PlaceEntity>
    suspend fun getAllPlaces(): Flow<List<PlaceEntity>>
    suspend fun savePlaceEntity(place: PlaceEntity)
    suspend fun updatePlaceEntity(place: PlaceEntity)
    suspend fun deletePlaceEntity(name: String)
}

class PlaceDatabaseImpl(
    private val database: VacationDatabase,
    private val dispatcherProvider: DispatcherProvider,
) : PlaceDatabase {

    override suspend fun getPlaceEntity(name: String): Flow<PlaceEntity> =
        withContext(dispatcherProvider.getIO()) {
            database.placeEntityQueries.getPlaceEntityByName(name)
                .asFlow()
                .map { placeInfo ->
                    placeInfo.executeAsOne()
                }
        }

    override suspend fun getAllPlaces(): Flow<List<PlaceEntity>> =
        withContext(dispatcherProvider.getIO()) {
            database.placeEntityQueries.getPlaceEntitys()
                .asFlow()
                .map { listPlaces ->
                    listPlaces.executeAsList().map { singlePlace ->
                        singlePlace
                    }
                }
        }

    override suspend fun savePlaceEntity(place: PlaceEntity) {
        database.placeEntityQueries.insertPlaceEntity(
            name = place.name,
            reviews = place.reviews,
            score = place.reviews.toDouble(),
            description = place.description,
            facilities = place.facilities,
            price = place.price,
            latitude = place.latitude,
            longitude = place.longitude,
            isFavorite = place.isFavorite,
            section = place.section,
            urlImage = place.urlImage
        )
    }

    override suspend fun updatePlaceEntity(place: PlaceEntity) {
        database.placeEntityQueries.updatePlaceEntityByName(
            name = place.name,
            reviews = place.reviews,
            score = place.reviews.toDouble(),
            description = place.description,
            facilities = place.facilities,
            price = place.price,
            latitude = place.latitude,
            longitude = place.longitude,
            isFavorite = place.isFavorite,
            section = place.section,
            urlImage = place.urlImage
        )
    }

    override suspend fun deletePlaceEntity(name: String) {
        database.placeEntityQueries.deletePlaceEntityByName(name)
    }
}

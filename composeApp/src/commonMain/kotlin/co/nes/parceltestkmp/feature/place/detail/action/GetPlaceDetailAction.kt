package co.nes.parceltestkmp.feature.place.detail.action

import co.nes.parceltestkmp.common.mvi.mvi.Action
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailsIntent
import co.nes.parceltestkmp.feature.place.model.toPlaceDetails
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class GetPlaceDetailAction(
    private val viewModel: PlaceDetailViewModel,
    private val dispatcherProvider: DispatcherProvider,
    private val placeUI: PlaceUI
) : Action {
    override suspend fun execute(): Unit = withContext(dispatcherProvider.getIO()) {
        val placeName = placeUI.name

        if (placeName.isNotEmpty()) {
            try {

                val localJob = async {
                    viewModel.repository.getPlaceByName(placeName)
                        .map { databaseData ->
                            databaseData?.toPlaceDetails()
                        }.firstOrNull()
                }

                val remoteJob = async {
                    viewModel.repository.fetchPlaceDetailsInfo(placeName)
                        .map {
                            it?.toPlaceDetails()
                        }.firstOrNull()
                }

                val localResult = localJob.await()

                localResult?.let { currentItem ->
                    println("ASPEN LOCAL: $currentItem")
                    val intent = PlaceDetailsIntent.Reduce.UpdatePlaceDetails(
                        currentItem
                    )
                    viewModel.onIntent(intent)
                }

                val remoteResult = remoteJob.await()

                remoteResult?.let { refreshedItem ->
                    println("ASPEN REMOTE: $refreshedItem $refreshedItem")

                    val intent = PlaceDetailsIntent.Reduce.UpdatePlaceDetails(
                        localResult?.let {
                            refreshedItem.copy(isFavorite = it.isFavorite)
                        } ?: refreshedItem
                    )
                    viewModel.onIntent(intent)
                }
            } catch (e: Exception) {
                println(e.stackTraceToString())
            }
        }
    }
}

package co.nes.parceltestkmp.feature.home.mvi.place.action

import co.nes.parceltestkmp.common.mvi.mvi.Action
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListIntent
import co.nes.parceltestkmp.feature.place.model.toPlaceUI
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class GetVacationInfo(
    private val viewModel: HomeViewModel,
    private val dispatcherProvider: DispatcherProvider
) : Action {
    override suspend fun execute() = withContext(dispatcherProvider.getIO()) {
        val localJob = async {
            viewModel.repository.getAllPlaceInfo().map { databaseData ->
                databaseData.map {
                    it.toPlaceUI(0)
                }
            }.firstOrNull()
        }

        val remoteJob = async {
            viewModel.repository.fetchVacationInfo().map {
                val popularList = it.places.popular.map { place ->
                    place.toPlaceUI(0)
                }

                val recommendedList = it.places.recommended.map { place ->
                    place.toPlaceUI(1)
                }
                val resultList = mutableListOf<PlaceUI>()
                resultList.addAll(popularList)
                resultList.addAll(recommendedList)
                resultList
            }.firstOrNull()
        }

        val localResult = localJob.await()
        if (!localResult.isNullOrEmpty()) {
            val intent = PlaceListIntent.Reduce.UpdatePlaceListList(
                localResult.filter { it.section == 0 },
                localResult.filter { it.section == 1 },
            )
            viewModel.onIntent(intent)
        }

        val remoteResult = remoteJob.await()
        if (!remoteResult.isNullOrEmpty()) {
            val intent = PlaceListIntent.Reduce.UpdatePlaceListList(
                remoteResult.filter { it.section == 0 },
                remoteResult.filter { it.section == 1 },
            )
            viewModel.onIntent(intent)
        }
    }
}

package co.nes.parceltestkmp.feature.home.mvi.place.action

import co.nes.parceltestkmp.common.mvi.mvi.Action
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.PlaceListIntent
import co.nes.parceltestkmp.feature.place.model.toPlaceUI
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class UpdateSelectedPlace(
    private val viewModel: HomeViewModel,
    private val dispatcherProvider: DispatcherProvider,
) : Action {
    override suspend fun execute() = withContext(dispatcherProvider.getIO()) {
        val localJob = async {
            viewModel.repository.getAllPlaceInfo().map { databaseData ->
                databaseData.map {
                    it.toPlaceUI()
                }
            }.firstOrNull()
        }

        val localResult = localJob.await()
        if (!localResult.isNullOrEmpty()) {
            val intent = PlaceListIntent.Reduce.UpdatePlaceList(
                localResult.filter { it.section == 0 },
                localResult.filter { it.section == 1 },
            )
            viewModel.onIntent(intent)
        }
    }
}

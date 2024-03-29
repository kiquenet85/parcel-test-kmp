package co.nes.parceltestkmp.feature.place.detail.place.action

import co.nes.parceltestkmp.common.mvi.mvi.Action
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailsCommand
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailsIntent
import co.nes.parceltestkmp.feature.place.model.toPlaceDetails
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

internal class UpdatePlaceDetailFavoriteAction(
    private val viewModel: PlaceDetailViewModel,
    private val dispatcherProvider: DispatcherProvider,
) : Action {
    override suspend fun execute(): Unit = withContext(dispatcherProvider.getIO()) {
        viewModel.viewState.value.placeDetail.let { place ->
            val itemDb = viewModel.repository.getPlaceByName(place.name).firstOrNull()

            itemDb?.let { dbValue ->
                val isFavoriteNewValue = place.isFavorite.not()
                val newItem = dbValue.copy(isFavorite = if (isFavoriteNewValue) 1 else 0)
                viewModel.repository.updatePlaceInfo(newItem)
                viewModel.onIntent(PlaceDetailsIntent.Reduce.UpdatePlaceDetails(newItem.toPlaceDetails()))
                viewModel.sendCommand(PlaceDetailsCommand.FavoriteUpdated)
            }
        }
    }
}

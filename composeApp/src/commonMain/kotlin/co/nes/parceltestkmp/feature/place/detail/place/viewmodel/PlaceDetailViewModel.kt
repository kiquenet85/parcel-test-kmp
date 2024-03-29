package co.nes.parceltestkmp.feature.place.detail.place.viewmodel

import co.nes.parceltestkmp.common.mvi.mvi.GeneralIntentAspenLogger
import co.nes.parceltestkmp.common.mvi.mvi.MVIViewModel
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.feature.place.detail.place.factory.PlaceDetailsActionFactory
import co.nes.parceltestkmp.feature.place.detail.place.factory.PlaceDetailsReducerFactory
import co.nes.parceltestkmp.feature.place.model.PlaceDetail
import co.nes.parceltestkmp.providers.DispatcherProvider

internal class PlaceDetailViewModel(
    private val actionFactory: PlaceDetailsActionFactory,
    private val reducerFactory: PlaceDetailsReducerFactory,
    private val dispatcherProvider: DispatcherProvider,
    val repository: PlaceInfoRepository,
    logger: GeneralIntentAspenLogger<PlaceDetailsIntent>,
): MVIViewModel<PlaceDetailsState, PlaceDetailsCommand, PlaceDetailsIntent>(logger) {
    override val initialState: PlaceDetailsState
        get() = PlaceDetailsState(
            placeDetail = PlaceDetail(
                name = "",
                rating =  0.0,
                reviews = 0,
                description = "",
                facilities = emptyList(),
                price = "",
                latitude = 0.0,
                longitude = 0.0,
                isFavorite = false
            )
        )

    override suspend fun handleIntent(intent: PlaceDetailsIntent) = when (intent) {
        is PlaceDetailsIntent.Screen ->
            actionFactory.fromIntent(intent, dispatcherProvider, viewModel = this).execute()
        is PlaceDetailsIntent.Reduce ->
            reducerFactory.fromIntent(intent).reduce(currentState).let { setState(it) }
    }

}

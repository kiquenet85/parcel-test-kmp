package co.nes.parceltestkmp.feature.home.mvi.place.viewmodel

import co.nes.parceltestkmp.common.mvi.mvi.GeneralIntentAspenLogger
import co.nes.parceltestkmp.common.mvi.mvi.MVIViewModel
import co.nes.parceltestkmp.feature.home.mvi.place.factory.PlaceListActionFactory
import co.nes.parceltestkmp.feature.home.mvi.place.factory.PlaceListReducerFactory
import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val actionFactory: PlaceListActionFactory,
    private val reducerFactory: PlaceListReducerFactory,
    private val dispatcherProvider: DispatcherProvider,
    val repository: PlaceInfoRepository,
    logger: GeneralIntentAspenLogger<PlaceListIntent>,
): MVIViewModel<PlaceListViewSate, PlaceListCommand, PlaceListIntent>(logger) {

    var selectedPlace : PlaceUI? = null
    init {
        viewModelScope.launch {
            handleIntent(PlaceListIntent.Screen.GetVacationInfo)
        }
    }
    override val initialState: PlaceListViewSate
        get() = PlaceListViewSate()

    override suspend fun handleIntent(intent: PlaceListIntent) = when (intent) {
        is PlaceListIntent.Screen ->
            actionFactory.fromIntent(intent, this, dispatcherProvider).execute()
        is PlaceListIntent.Reduce ->
            reducerFactory.fromIntent(intent).reduce(currentState).let { setState(it) }
    }

    fun toPlaceDetails(selectedPlace : PlaceUI) {
        this.selectedPlace = selectedPlace
        sendCommand(PlaceListCommand.SelectPlaceList)
    }

}

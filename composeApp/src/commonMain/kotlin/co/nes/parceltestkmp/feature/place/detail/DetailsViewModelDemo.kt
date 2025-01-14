package co.nes.parceltestkmp.feature.place.detail
import co.nes.parceltestkmp.common.view_model.ViewModel
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.feature.place.data.remote.PlaceDetail
import co.nes.parceltestkmp.providers.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

class DetailsViewModelDemo(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: PlaceInfoRepository
) : ViewModel() {
    private var _singlePlaceInfo = MutableStateFlow("")
    val singlePlaceInfo = _singlePlaceInfo.asStateFlow()
    private var _allPlaceInfo = MutableStateFlow("")
    val allPlaceInfo = _allPlaceInfo.asStateFlow()
    private var _placeInfoByName = MutableStateFlow("")
    val placeInfoByName = _placeInfoByName.asStateFlow()
    private var _vacationInfoData = MutableStateFlow("")
    val vacationInfoData = _vacationInfoData.asStateFlow()

    fun getPlaceInfo() {
        viewModelScope.launch {
            repository.fetchPlaceDetailsInfo(PlaceDetail.CentralPark.id).collect {
                _singlePlaceInfo.value = it.toString()
            }
        }
    }
    fun getAllPlaceInfo() {
        viewModelScope.launch(dispatcherProvider.getIO()) {
            repository.getAllPlaceInfo().collect {
                _allPlaceInfo.value = it.toString()
            }
        }
    }
    fun getPlaceByName(name: String) {
        viewModelScope.launch {
            repository.getPlaceByName(name).collect {
                _placeInfoByName.value = it.toString()
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    fun getVacationInfoData() {
        viewModelScope.launch {
            repository.getVacationInfoData().collect {
                _vacationInfoData.value = it.toString()
            }
        }
    }
}
 
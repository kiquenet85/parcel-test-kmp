package co.nes.parceltestkmp.common.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidXViewModelScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual abstract class ViewModel actual constructor() : ViewModel() {
    actual val viewModelScope: CoroutineScope = androidXViewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}
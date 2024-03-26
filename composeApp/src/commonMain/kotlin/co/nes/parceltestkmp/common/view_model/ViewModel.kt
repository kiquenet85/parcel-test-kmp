package co.nes.parceltestkmp.common.view_model

import kotlinx.coroutines.CoroutineScope

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect abstract class ViewModel() {
    val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
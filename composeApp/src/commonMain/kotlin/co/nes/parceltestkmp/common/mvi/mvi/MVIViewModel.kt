package co.nes.parceltestkmp.common.mvi.mvi

import androidx.annotation.CallSuper
import co.nes.parceltestkmp.common.view_model.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Abstract/Starter class for View Models that use the MVI Pattern
 */
abstract class MVIViewModel<VIEWSTATE, VIEWCOMMAND, VIEWINTENT>(
    private val intentLogger: IntentLogger<VIEWINTENT>
) : ViewModel() {
    /**
     * STATE DATA boilerplate
     */
    private val _viewState by lazy { MutableStateFlow(initialState) }

    /**
     * Implementing ViewModels should use this function to set a new state
     */
    @CallSuper
    protected fun setState(state: VIEWSTATE) {
        _viewState.value = state
    }

    /**
     * Subscribe-able flow for ViewState changes
     */
    val viewState: StateFlow<VIEWSTATE>
        get() = _viewState

    /**
     * Single reference to the current state. Used commonly by model implementations;
     * available to views as well.
     */
    val currentState: VIEWSTATE
        get() = _viewState.value

    /**
     * Initial non-null state.
     */
    abstract val initialState: VIEWSTATE

    /**
     * Control Data boilerplate
     */
    private val _viewCommand by lazy {
        MutableSharedFlow<ConsumableValue<VIEWCOMMAND>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    /**
     * Implementing ViewModels should use this function to send Commands
     */
    @CallSuper
    fun sendCommand(command: VIEWCOMMAND) {
        viewModelScope.launch {
            _viewCommand.emit(ConsumableValue(command))
        }
    }

    /**
     * Subscribe-able Command pattern
     *
     * Handle as such to ensure consumption:
     * viewCommand.onEach {
     *      it.consume { VIEWCOMMAND -> *** }
     * }
     */
    val viewCommand: SharedFlow<ConsumableValue<VIEWCOMMAND>>
        get() = _viewCommand

    /**
     * Event receiver which handles viewmodel processing and should update the State and/or Control
     */
    open fun onIntent(intent: VIEWINTENT) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }

    private val intentFlow = MutableSharedFlow<VIEWINTENT>()

    abstract suspend fun handleIntent(intent: VIEWINTENT)

    init {
        viewModelScope.launch {
            intentFlow.collect { intent ->
                intentLogger.log(intent)
                handleIntent(intent)
            }
        }
    }
}

/**
 * Used when we don't need any command-control pattern, such as toasts/dialogs
 */
class NoViewCommands

package co.nes.parceltestkmp.feature.place.detail

import co.nes.parceltestkmp.getPlatformName
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update

class DefaultDetailComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : DetailComponent, ComponentContext by componentContext {

    // Consider preserving and managing the state via a store
    private val state = MutableValue(DetailComponent.Model())
    override val model: Value<DetailComponent.Model> = state

    override fun onUpdateGreetingText() {
       state.update { it.copy(greetingText = "Welcome from ${getPlatformName()}") }
    }

    override fun onBackClicked() {
        onFinished()
    }
}

package co.nes.parceltestkmp.feature.place.detail

import com.arkivanov.decompose.value.Value

interface DetailComponent {

    val model: Value<Model>

    fun onUpdateGreetingText()
    fun onBackClicked()

    data class Model(
        val greetingText: String = "Welcome from Decompose!"
    )
}

package co.nes.parceltestkmp.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import co.nes.parceltestkmp.feature.home.HomeComponent
import co.nes.parceltestkmp.feature.place.detail.DetailComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Detail(val component: DetailComponent) : Child()
    }
}

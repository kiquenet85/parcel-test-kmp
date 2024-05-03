package co.nes.parceltestkmp.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

import co.nes.parceltestkmp.feature.home.DefaultHomeComponent
import co.nes.parceltestkmp.feature.home.HomeComponent
import co.nes.parceltestkmp.feature.place.detail.DefaultDetailComponent
import co.nes.parceltestkmp.feature.place.detail.DetailComponent

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Home,
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, childComponentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Home -> RootComponent.Child.Home(mainComponent(childComponentContext))
            is Config.Detail -> RootComponent.Child.Detail(welcomeComponent(childComponentContext))
        }

    private fun mainComponent(componentContext: ComponentContext): HomeComponent =
        DefaultHomeComponent(
            componentContext = componentContext,
            onShowWelcome = { navigation.push(Config.Detail) },
        )

    private fun welcomeComponent(componentContext: ComponentContext): DetailComponent =
        DefaultDetailComponent(
            componentContext = componentContext,
            onFinished = navigation::pop,
        )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(index = toIndex)
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Home : Config

        @Serializable
        data object Detail : Config
    }
}

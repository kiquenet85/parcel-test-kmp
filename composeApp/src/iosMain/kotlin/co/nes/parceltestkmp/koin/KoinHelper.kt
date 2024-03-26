package co.nes.parceltestkmp.koin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import co.nes.parceltestkmp.viewmodel.ViewModel
import org.koin.core.Koin
import org.koin.core.context.startKoin
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object KoinContainer {
    lateinit var koinApp: Koin

    fun initKoin() {
        koinApp = startKoin {
            modules(
                IOSMainModules.getDatabaseModule(),

                SharedModules.getDispatcherProviderModule(),
                SharedModules.getVacationModule(),
            )
        }.koin
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object KoinHelperKmp {
    @Composable
    actual inline fun <reified T : Any> koinInject(): T {
        return KoinContainer.koinApp.inject<T>().value
    }

    @Composable
    actual inline fun <reified T : ViewModel> getViewModel(): T {
        return remember { KoinContainer.koinApp.inject<T>().value }
    }
}
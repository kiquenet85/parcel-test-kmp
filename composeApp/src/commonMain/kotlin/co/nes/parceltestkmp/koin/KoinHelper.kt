package co.nes.parceltestkmp.koin

import androidx.compose.runtime.Composable
import co.nes.parceltestkmp.viewmodel.ViewModel

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object KoinHelperKmp {
    @Composable
    inline fun <reified T : Any> koinInject(): T

    @Composable
    inline fun <reified T : ViewModel> getViewModel(): T
}

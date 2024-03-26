package co.nes.parceltestkmp.koin

import androidx.compose.runtime.Composable
import co.nes.parceltestkmp.common.view_model.ViewModel

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object KoinHelperKmp {
    @Composable
    actual inline fun <reified T : Any> koinInject(): T {
        return org.koin.compose.koinInject()
    }

    @Composable
    actual inline fun <reified T : ViewModel> getViewModel(
    ): T {
        return org.koin.androidx.compose.getViewModel()
    }
}

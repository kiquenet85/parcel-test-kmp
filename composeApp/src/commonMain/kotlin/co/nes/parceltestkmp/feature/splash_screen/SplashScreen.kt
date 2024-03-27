package co.nes.parceltestkmp.feature.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.feature.home.HomeScreen
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.theme.AspenTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import parceltestkmp.composeapp.generated.resources.AspenSplashScreen
import parceltestkmp.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
class SplashScreen: ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.AspenSplashScreen),
            contentDescription = "Splash Screen Aspen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )

        AspenButton(
            onClick = {
                navigator.push(HomeScreen())
            }
        ) {
            AspenText(
                "Explore",
                color = AspenTheme.colors.onPrimary,
            )
        }
    }
})

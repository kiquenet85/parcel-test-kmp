package co.nes.parceltestkmp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.nes.parceltestkmp.common.ComposableScreen
import co.nes.parceltestkmp.feature.place.explore.ExploreSection
import co.nes.parceltestkmp.ui.components.AspenBottomBar
import co.nes.parceltestkmp.ui.components.AspenBottomTab
import co.nes.parceltestkmp.ui.components.AspenBottomTab.Companion.bottomTabTitles
import co.nes.parceltestkmp.ui.components.AspenButton
import co.nes.parceltestkmp.ui.components.AspenText
import co.nes.parceltestkmp.ui.components.AspenTopBar
import co.nes.parceltestkmp.ui.theme.AspenTheme

class HomeScreen : ComposableScreen({
    val navigator = LocalNavigator.currentOrThrow
    val showTopBar = remember { mutableStateOf(true) }
    val selectedBottomBarIndex = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AspenTheme.colors.background
            ),
        topBar = { if (showTopBar.value) AspenTopBar() },
        bottomBar = {
            AspenBottomBar(
                bottomTabTitles,
                selectedIndex = selectedBottomBarIndex.value
            ) {
                selectedBottomBarIndex.value = it
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding(),
                        start = innerPadding.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,  // Adding left padding
                        end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            when (val currentBottomBarTabIdx = selectedBottomBarIndex.value) {
                AspenBottomTab.Explore.idx -> {
                    showTopBar.value = true
                    ExploreSection(innerPadding, navigator)
                }

                else -> {
                    showTopBar.value = false
                    AspenText("${bottomTabTitles[currentBottomBarTabIdx].title} content")

                    AspenButton(onClick = {
                        navigator.pop()
                    }) {
                        AspenText(
                            "Go to Splash Screen",
                            color = AspenTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
})

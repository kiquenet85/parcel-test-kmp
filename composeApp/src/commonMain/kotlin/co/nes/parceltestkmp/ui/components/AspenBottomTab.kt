package co.nes.parceltestkmp.ui.components

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.heart
import parceltestkmp.composeapp.generated.resources.home
import parceltestkmp.composeapp.generated.resources.ticket
import parceltestkmp.composeapp.generated.resources.profile

@OptIn(ExperimentalResourceApi::class)
sealed class AspenBottomTab(
    val idx: Int, val title: String, val icon: DrawableResource
) {
    data object Explore : AspenBottomTab(0, "Explore", Res.drawable.home)
    data object Bookings : AspenBottomTab(1, "Bookings", Res.drawable.ticket)
    data object Favorite : AspenBottomTab(2, "Favorite", Res.drawable.heart)
    data object Profile : AspenBottomTab(3, "Profile", Res.drawable.profile)

    companion object {
        val bottomTabTitles =
            listOf(Explore, Bookings, Favorite, Profile)
    }
}

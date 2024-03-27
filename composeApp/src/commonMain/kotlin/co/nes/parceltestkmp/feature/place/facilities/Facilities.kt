package co.nes.parceltestkmp.feature.place.facilities

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import parceltestkmp.composeapp.generated.resources.Res
import parceltestkmp.composeapp.generated.resources.bath_tub
import parceltestkmp.composeapp.generated.resources.food
import parceltestkmp.composeapp.generated.resources.pool
import parceltestkmp.composeapp.generated.resources.wifi

@OptIn(ExperimentalResourceApi::class)
sealed class Facilities(
    val idx: Int, val name: String, val icon: DrawableResource
) {
    data object Heater : Facilities(0, "1 Heater", Res.drawable.bath_tub)
    data object Dinner : Facilities(1, "Dinner", Res.drawable.food)
    data object Tub : Facilities(2, "1 Tub", Res.drawable.pool)
    data object Pool : Facilities(3, "Pool", Res.drawable.wifi)

    companion object {
        val facilitiesOptions = listOf(Heater, Dinner, Tub, Pool)
    }
}

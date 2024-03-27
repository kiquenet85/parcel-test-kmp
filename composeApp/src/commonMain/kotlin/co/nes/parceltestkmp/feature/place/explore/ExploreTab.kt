package co.nes.parceltestkmp.feature.place.explore

sealed class ExploreTab(val idx: Int, val title: String) {
    data object Place : ExploreTab(0, "Place")
    data object Hotel : ExploreTab(1, "Hotel")
    data object Food : ExploreTab(2, "Food")
    data object Adventure : ExploreTab(3, "Adventure")
    data object Activities : ExploreTab(4, "Activities")

    companion object {
        val exploreTabs =
            listOf(Place, Hotel, Food, Adventure, Activities)
    }
}

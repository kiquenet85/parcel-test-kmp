package co.nes.parceltestkmp.feature.place.model

//import co.nes.parceltestkmp.feature.place.horizontalList.mvi.PlaceListView
import conesparceltestkmp.PlaceEntity

fun PlaceInfoDTO.toPlaceEntity(section: Long): PlaceEntity {
    return PlaceEntity(
        name = name,
        score = rating,
        reviews = reviews.toLong(),
        description = description,
        facilities = facilities.concat(),
        price = price,
        latitude = latitude,
        longitude = longitude,
        isFavorite = if (isFavorite) 1 else 0,
        section = section,
        urlImage = imageURL
    )
}

fun Place.toPlaceEntity(section: Int): PlaceEntity {
    return PlaceEntity(
        name = name,
        urlImage = image,
        score = rating,
        reviews = 0,
        description = "no description",
        facilities = "No facilities",
        price = 0.toDouble(),
        latitude = 0.toDouble(),
        longitude = 0.toDouble(),
        isFavorite = if (isFavorite) 1 else 0,
        section = section.toLong()
    )
}

fun List<String>.concat() = this.joinToString(",") { it }.takeWhile { !it.isDigit() }

package co.nes.parceltestkmp.feature.place.model

import co.nes.parceltestkmp.feature.home.mvi.place.model.PlaceUI
import co.nes.parceltestkmp.feature.place.facilities.Facility
import conesparceltestkmp.PlaceEntity

fun PlaceDetailDTO.toPlaceEntity(section: Long): PlaceEntity {
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
        urlImage = imageUrl,
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

fun PlaceDetail.toPlaceEntity(urlImage: String): PlaceEntity {
    return PlaceEntity(
        name = name,
        urlImage = urlImage,
        score = rating,
        reviews = 0,
        description = description,
        facilities = facilities.toStringList().concat(),
        price = 0.toDouble(),
        latitude = 0.toDouble(),
        longitude = 0.toDouble(),
        isFavorite = if (isFavorite) 1 else 0,
        section = -1
    )
}

fun Place.toPlaceUI(section: Int): PlaceUI {
    return PlaceUI(
        name = name,
        rating = rating,
        section = section,
        imageUrl = imageUrl,
        isFavorite = isFavorite
    )
}
fun PlaceEntity.toPlaceUI(section : Int): PlaceUI {
    return PlaceUI(
        name = name,
        rating = score,
        section = section,
        imageUrl = urlImage,
        isFavorite = isFavorite == 1L
    )
}
fun PlaceEntity.toPlaceDetails(): PlaceDetail {
    return PlaceDetail(
        name = name,
        rating = score,
        reviews = reviews.toInt(),
        description = description,
        facilities = facilities.split(",").toFacilitiesEnum(),
        price = price.toString(),
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite == 1L
    )
}

fun PlaceDetailDTO.toPlaceDetails(): PlaceDetail {
    return PlaceDetail(
        name = name,
        rating = rating,
        reviews = reviews,
        description = description,
        facilities = facilities.toFacilitiesEnum(),
        price = price.toString(),
        latitude = latitude,
        longitude = longitude,
        isFavorite = isFavorite
    )
}


fun List<String>.concat() = this.joinToString(",") { it }.takeWhile { !it.isDigit() }
private fun List<String>.toFacilitiesEnum(): List<Facility> {
    val list = this.map {
        Facility.getFacility(it)
    }
    return list.filterNotNull()
}

private fun List<Facility>.toStringList(): List<String> {
    return this.map {
        it.name
    }
}


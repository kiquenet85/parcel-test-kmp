package co.nes.parceltestkmp.feature.place.model

import conesparceltestkmp.PlaceInfo

fun PlaceInfo.toPlaceInfoDto(): PlaceInfoDTO {
    return PlaceInfoDTO(
        name = name,
        rating = score,
        reviews = reviews.toInt(),
        description = description,
        facilities = facilities.split(","),
        price = price,
        latitude = latitude,
        longitude = longitude

    )
}

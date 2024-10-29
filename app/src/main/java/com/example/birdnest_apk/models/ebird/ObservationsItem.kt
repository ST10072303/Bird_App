package com.example.birdnest_apk.models.ebird

data class ObservationsItem(
    val comName: String,
    val howMany: Int,
    val lat: Double,
    val lng: Double,
    val locId: String,
    val locName: String,
    val locationPrivate: Boolean,
    val obsDt: String,
    val obsReviewed: Boolean,
    val obsValid: Boolean,
    val sciName: String,
    val speciesCode: String
)
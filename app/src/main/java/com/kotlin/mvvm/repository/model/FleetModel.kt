package com.kotlin.mvvm.repository.model

import com.kotlin.mvvm.ui.adapter.ListItemView


data class FleetModel(
    val poiList: List<Poi>
) : ListItemView()

data class Poi(
    val coordinate: Coordinate,
    val fleetType: String,
    val heading: Double,
    val id: Int
) : ListItemView()

data class Coordinate(
    val latitude: Double,
    val longitude: Double
) : ListItemView()
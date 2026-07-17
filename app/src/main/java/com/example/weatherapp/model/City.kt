package com.example.weatherapp.model

import com.example.weatherapp.db.fb.FBCity
import com.google.android.gms.maps.model.LatLng

data class City(
    val name : String,
    val weather: String? = null,
    val location: LatLng? = null
)


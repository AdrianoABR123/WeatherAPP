package com.example.weatherapp.ui.components.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route{
    @Serializable
    data object home : Route
    @Serializable
    data object List: Route
    @Serializable
    data object Map: Route
    companion object {
        val Home: Any
    }
}

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: Route
){
    data object HomeButton :
            BottomNavItem(
                title = "Inicio",
                icon = Icons.Default.Home,
                route = Route.home
            )
    data object ListButton :
            BottomNavItem(
                title = "Favoritos",
                icon = Icons.Default.Favorite,
                route = Route.List
            )
    data object MapButton :
            BottomNavItem(
                title = "Mapa",
                icon = Icons.Default.LocationOn,
                route = Route.Map
            )
}
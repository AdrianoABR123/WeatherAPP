package com.example.weatherapp.ui.pages

import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapPage(modifier: Modifier = Modifier, viewModel: MainViewModel){
    Column(
        modifier = modifier.fillMaxSize()
            .background(Color.Gray)
            .wrapContentSize(Alignment.Center),
    ){
        val recife = remember { MarkerState(LatLng(-8.0631628, -34.8711403)) }
        val caruaru = remember { MarkerState(LatLng(-8.28383, -35.97605)) }
        val joaopessoa = remember { MarkerState( LatLng(-7.120064, -34.879905)) }

        val camPosState = rememberCameraPositionState ()

        val context = LocalContext.current
        val hasLocationPermission by remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED
            )
        }
        GoogleMap (
            modifier = Modifier.fillMaxSize(),
            onMapClick = {
                viewModel.add("Cidade@${it.latitude}:${it.longitude}", location = it)
            },
            cameraPositionState = camPosState,
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)
            ) {
            viewModel.cities.forEach {
                if (it.location != null) {
                    Marker( state = MarkerState(position = it.location),
                        title = it.name, snippet = "${it.location}")
                }
            }
            Marker(
                state = recife,
                title = "Recife",
                snippet = "Marcador em Recife",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
            Marker(
                state = caruaru,
                title = "Caruaru",
                snippet = "Marcador em Caruaru",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
            Marker(
                state = joaopessoa,
                title = "João Pessoa",
                snippet = "Marcador em João Pessoa",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
        }
    }
}
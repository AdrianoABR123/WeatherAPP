package com.example.weatherapp.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
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
        val caruaru = remember { MarkerState(LatLng(-8.28111, -35.97611)) }
        val joaopessoa = remember { MarkerState( LatLng(-7.119777, -34.862444)) }
        val camPosState = rememberCameraPositionState ()
        GoogleMap (
            modifier = Modifier.fillMaxSize(),
            onMapClick = {
                viewModel.add("Cidade@${it.latitude}:${it.longitude}", location = it)
            },
            cameraPositionState = camPosState
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
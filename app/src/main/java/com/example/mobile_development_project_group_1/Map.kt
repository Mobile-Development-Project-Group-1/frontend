package com.example.mobile_development_project_group_1

import android.location.LocationRequest
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.dynamic.IObjectWrapper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MyMap (){
    val pubPlaceVM = viewModel<PubPlaceViewModel>(LocalContext.current as ComponentActivity)
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)

    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(rememberMapLifecycleObserver(mapView))

    pubPlaceVM.getCurrentUserLocation()

        AndroidView(
            factory = {
                mapView.apply {
                    CoroutineScope(Dispatchers.Main).launch {
                        mapView.getMapAsync {
                            it.mapType = 1
                            it.uiSettings.isZoomControlsEnabled = true

                            val current = LatLng(pubPlaceVM.currentg.latitude, pubPlaceVM.currentg.longitude) // Oulu
                            val currentLocation =  MarkerOptions()
                                .title("You")
                                .position(current)
                                .icon(BitmapDescriptorFactory.fromBitmap(ResourcesCompat.getDrawable(resources, R.drawable.ic_current_location, null)!!.toBitmap()))
                            it.addMarker( currentLocation )
                            it.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 11f))
                            pubPlaceVM.pubPlaceLocations.forEach { elem ->
                                val mark = LatLng(elem.value.coor.latitude, elem.value.coor.longitude)
                                val markerOptions =  MarkerOptions()
                                    .title(elem.value.title)
                                    .position(mark)
                                it.addMarker( markerOptions )
                                it.addPolyline(PolylineOptions().add(mark).add(current).width(3F).color(
                                    0xff0000ff.toInt()
                                ))
                            }
                        }
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.914f)
        )

}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver {
    return remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }
}


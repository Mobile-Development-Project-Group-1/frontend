package com.example.mobile_development_project_group_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun MyMap (){
    val fAuth = Firebase.auth
    val fireStore = Firebase.firestore
    var currentUserRoute by remember { mutableStateOf("") }
    val pubPlaceVM = viewModel<PubPlaceViewModel>(LocalContext.current as ComponentActivity)
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)

    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(rememberMapLifecycleObserver(mapView))

    pubPlaceVM.getCurrentUserLocation()

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserRoute = it.get("root").toString()
        }

        AndroidView(
            factory = {
                mapView.apply {
                    CoroutineScope(Dispatchers.Main).launch {
                        mapView.getMapAsync {
                            it.mapType = 1
                            it.uiSettings.isZoomControlsEnabled = true

                            val current = LatLng(pubPlaceVM.currentg.latitude, pubPlaceVM.currentg.longitude) // Oulu
                            val currentLocation =  MarkerOptions()
                                .title(
                                    if (
                                        currentUserRoute == "USER"
                                        || currentUserRoute == "MANAGER"
                                        || currentUserRoute == "ADMIN"
                                    ) {
                                        userVM.userdata.value["firstName"].toString()
                                    } else {
                                        "Your location"
                                    }
                                )
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


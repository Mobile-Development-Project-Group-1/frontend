package com.example.mobile_development_project_group_1

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PubPlaceViewModel: ViewModel() {
    private val fireStore = Firebase.firestore
    var pubPlaceLocations = mutableMapOf<String, PubPlace>()
    lateinit var currentg :GeoPoint

    fun getPubPlaceInfo() {
        fireStore
            .collection("public_places")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    pubPlaceLocations[document.id] =
                        PubPlace(
                            document.get("title").toString(),
                            document.get("coor") as GeoPoint,
                            document.get("description").toString(),
                            document.get("pub_img_url").toString(),
                            document.get("m_id").toString(),
                            document.get("workdays").toString(),
                            document.get("weekend").toString(),
                            document.get("contactUs").toString(),
                            document.get("weblink").toString(),
                            document.get("address").toString(),
                            document.get("event") as ArrayList<Map<String,Any>>

                        )
                    Log.d("*************", pubPlaceLocations[document.id]?.event.toString())
                }
                Log.d("result", pubPlaceLocations.toString())
            }

    }

    fun getCurrentUserLocation() {
        fireStore
            .collection("current_location")
            .document("gdYrqnRmkj8il2zBUcbG")
            .get()
            .addOnSuccessListener {
                var tempGeoPoint = it.getGeoPoint("current_location")
                Log.d("0000000", tempGeoPoint.toString())
               if ( tempGeoPoint != null){
                   currentg = tempGeoPoint
               }
                Log.d("pppppp", currentg.toString())
            }

    }
}
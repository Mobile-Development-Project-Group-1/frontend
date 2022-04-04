package com.example.mobile_development_project_group_1

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PubPlaceViewModel: ViewModel() {
    private val fireStore = Firebase.firestore
    var pubPlaceLocations = mutableMapOf<String, PubPlace>()

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
                            document.get("description").toString()
                        )
                    Log.d("*************", pubPlaceLocations[document.id]?.title.toString())
                }
            }
    }
}
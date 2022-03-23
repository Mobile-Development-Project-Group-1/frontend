package com.example.mobile_development_project_group_1

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PubPlaceViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    fun addNew(
        title: String,
        address: String,
        contactEmail: String,
        contactPhoneNumber: String,
        webLink: String,
        facebook: String,
        instagram: String,
        description: String,
        workingHours: String
    ) {

        if (
            title.isNotEmpty()
            && address.isNotEmpty()
            && contactEmail.isNotEmpty()
            && contactPhoneNumber.isNotEmpty()
            && webLink.isNotEmpty()
            && facebook.isNotEmpty()
            && instagram.isNotEmpty()
            && description.isNotEmpty()
            && workingHours.isNotEmpty()
        ) {

            val pubPlaceInfo = hashMapOf(
                "m_id" to fAuth.currentUser!!.uid,
                "title" to title,
                "address" to address,
                "contactEmail" to contactEmail,
                "contactPhoneNumber" to contactPhoneNumber,
                "webLink" to webLink,
                "facebook" to facebook,
                "instagram" to instagram,
                "description" to description,
                "workingHours" to workingHours
            )

            fireStore
                .collection("public_places")
                .document()
                .set(pubPlaceInfo)
                .addOnSuccessListener {
                    Log.d("********", "Public place added successfully")
                }
                .addOnFailureListener {
                    Log.d("********", "Something went wrong :(")
                }

        } else {
            Log.d("********", "Please, fill in all fields.")
        }
    }
}
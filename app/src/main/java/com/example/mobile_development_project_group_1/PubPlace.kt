package com.example.mobile_development_project_group_1

import com.google.firebase.firestore.GeoPoint

data class PubPlace(
    val title: String,
    val coor: GeoPoint,
    val description: String
    )
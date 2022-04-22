package com.example.mobile_development_project_group_1

import com.google.firebase.firestore.GeoPoint

data class PubPlace(
    val title: String,
    val coor: GeoPoint,
    val description: String,
    val pub_img_url :String,
    val m_id:String,
    val workdays :String,
    val weekend:String,
    val contactUs:String,
    val weblink:String,
    val address:String,
    val event : ArrayList<Map<String,Any>>
    )
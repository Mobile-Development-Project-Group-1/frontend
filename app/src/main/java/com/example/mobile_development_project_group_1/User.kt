package com.example.mobile_development_project_group_1

data class User(
    val firstName: String,
    val lastName: String,
    val address: String,
    val phoneNumber: String,
    val route: String,
    val pictureUrl: String = "https://firebasestorage.googleapis.com/v0/b/mdp-firebase-g1.appspot.com/o/img.png?alt=media&token=7a17860b-7342-4c81-b1db-b26bb15892a8"
)
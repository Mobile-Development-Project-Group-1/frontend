package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun HomeView() {
    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth
    var currentUserRoute by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserRoute = it.get("route").toString()
        }

    Column {
        Text(text = "Main page")
        when (currentUserRoute) {
            ADMIN_ROUTE -> Text(text = "You are $currentUserRoute")
            MANAGER_ROUTE -> Text(text = "You are $currentUserRoute")
            USER_ROUTE -> Text(text = "You are $currentUserRoute")
        }
    }
}
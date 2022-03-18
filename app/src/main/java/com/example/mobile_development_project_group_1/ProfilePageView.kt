package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePageView() {
    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth

    var currentUserFirstName by remember { mutableStateOf("") }
    var currentUserLastName by remember { mutableStateOf("") }
    var currentUserAddress by remember { mutableStateOf("") }
    var currentUserPhoneNumber by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserFirstName = it.get("firstName").toString()
            currentUserLastName = it.get("lastName").toString()
            currentUserAddress = it.get("address").toString()
            currentUserPhoneNumber = it.get("phoneNumber").toString()
        }

    Column {
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholder = { Text(text = currentUserFirstName) }
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholder = { Text(text = currentUserLastName) }
        )
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            placeholder = { Text(text = currentUserAddress) }
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = { Text(text = currentUserPhoneNumber) }
        )
    }
}
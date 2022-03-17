package com.example.mobile_development_project_group_1

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    var username = mutableStateOf("")

    fun loginUser(email: String, pw: String) {
        Firebase.auth
            .signInWithEmailAndPassword(email, pw)
            .addOnSuccessListener {
                username.value = email
            }
    }

    fun logout() {
        Firebase.auth.signOut()
        username.value = ""
    }
}
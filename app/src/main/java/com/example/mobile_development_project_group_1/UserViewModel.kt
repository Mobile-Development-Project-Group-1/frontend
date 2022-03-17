package com.example.mobile_development_project_group_1

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    fun signUpUser(firstName: String, lastName: String, email: String, pw: String, phoneNumber: String, address: String, route: String) {

        if (email.isNotEmpty() || pw.isNotEmpty()) {
            fAuth
                .createUserWithEmailAndPassword(email, pw)
                .addOnFailureListener {
                    errorMessage.value = ""
                    successMessage.value = "Registration completed successfully"

                    fireStore
                        .collection("users")
                        .document(it.)
                }
        }

    }

    fun logInUser(email: String, pw: String) {
        if (email.isNotEmpty() || pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    errorMessage.value = ""
                    successMessage.value = "Logged in successfully"
                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                    successMessage.value = ""
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
            successMessage.value = ""
        }
    }

    fun logout() {
        fAuth.signOut()
        errorMessage.value = ""
        successMessage.value = ""
    }
}
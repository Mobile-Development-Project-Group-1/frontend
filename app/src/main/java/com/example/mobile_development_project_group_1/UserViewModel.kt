package com.example.mobile_development_project_group_1

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    var isAnyUser = mutableStateOf(false)

    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    private fun loggedIn(){
        isAnyUser.value = !isAnyUser.value
    }

    fun logInUser(email: String, pw: String) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    loggedIn()
                    Log.d("********", "Logged in successfully")
                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun signUpUser(email: String, pw: String, firstName: String, lastName: String, address: String, phoneNumber: String, route: String) {

        if (email.isNotEmpty() || pw.isNotEmpty()) {

            fAuth
                .createUserWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d("********", "Registration completed successfully")

                    fireStore
                        .collection("users")
                        .document(it.user!!.uid)
                        .set( User(firstName, lastName, address, phoneNumber, route) )
                        .addOnSuccessListener {
                            logInUser(email, pw)
                            Log.d("********", "User's information added successfully!")
                        }
                        .addOnFailureListener { error ->
                            Log.d("********", error.message.toString())
                        }
                }
        } else {
            Log.d("********", "Please, fill email and password fields")
        }

    }

    fun logout() {
        fAuth.signOut()
        loggedIn()
        errorMessage.value = ""
        successMessage.value = ""
        isAnyUser.value = false
    }

    fun deleteUser() {
        loggedIn()

        if (fAuth.currentUser != null) {

            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .delete()
                .addOnSuccessListener {
                    Log.d("********", "User deleted from FireBase successfully")

                }
                .addOnFailureListener {
                    Log.d("********", "Something went wrong :(")
                }


            fAuth.currentUser!!
                .delete()
                .addOnCompleteListener {
                    Log.d("********", "User deleted")
                }
        }
    }
}
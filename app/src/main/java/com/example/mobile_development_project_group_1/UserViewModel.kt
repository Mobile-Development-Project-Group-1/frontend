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

    fun logInUser(email: String, pw: String) {
        if (email.isNotEmpty() || pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    changeSomething()
                    Log.d("********", "Logged in successfully")
                    Log.d("********", isAnyUser.value.toString())

                }
                .addOnFailureListener {
                    Log.d("********", "Incorrect email or password")
                }
        } else {
            Log.d("********", "Please, fill email and password fields")
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
                            Log.d("********", "User's information added successfully!")
                        }
                        .addOnFailureListener { error ->
                            Log.d("********", error.message.toString())
                        }
                }
                .addOnFailureListener {
                    Log.d("********", "Something went wrong :(")
                }

            logInUser(email, pw)

        } else {
            Log.d("********", "Please, fill email and password fields")
        }

    }

    fun logout() {
        fAuth.signOut()
        errorMessage.value = ""
        successMessage.value = ""
        isAnyUser.value = false
    }
    fun changeSomething(){
        isAnyUser.value = true
    }

    fun deleteUser() {
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
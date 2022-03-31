package com.example.mobile_development_project_group_1

import android.media.Image
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    private val storage    = Firebase.storage
    private val  ref     =  storage.reference
    var isAnyUser = mutableStateOf(false)

    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var userdata = mutableStateOf(mapOf<String,Any>())


    fun logInUser(email: String, pw: String, navController: NavHostController) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {

            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d("********", "Logged in successfully")
                    navController.navigate(HOME_ROUTE)
                    isAnyUser.value = true
                    errorMessage.value = ""

                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun signUpUser(email: String, pw: String, firstName: String, lastName: String, address: String, phoneNumber: String, route: String, navController: NavHostController) {

        if (email.isNotEmpty() && pw.isNotEmpty()) {

            fAuth
                .createUserWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    isAnyUser.value = true
                    logInUser(email, pw, navController)
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
                    errorMessage.value = "Check your email and password again"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }

    }

    fun logout() {
        fAuth.signOut()
        isAnyUser.value = false
        errorMessage.value = ""
        successMessage.value = ""
    }

    fun deleteUser() {
        isAnyUser.value = false

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

    fun getUserData(){
        fireStore
            .collection("users")
            .document(fAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                val result = it.data
                var  temp  = mutableMapOf<String,Any>()
                if (result != null){
                    temp = result
                }
                userdata.value = temp


            }

    }
    fun setProfileImage(u:Uri){
        var path = fAuth.currentUser?.uid.toString()
        ref.child(path).putFile(u)
            .addOnSuccessListener {
                val result = it.metadata!!.reference!!.downloadUrl;
                result.addOnSuccessListener { doc ->
                    var temp = doc.toString()
                    var tempUserdata = userdata.value.toMutableMap()
                     tempUserdata["pictureUrl"] = temp

                    fireStore
                        .collection("users")
                        .document(fAuth.currentUser!!.uid)
                        .set(tempUserdata)
                        .addOnSuccessListener {
                            Log.d("********", "Profile photos are being updated")
                            getUserData()
                        }
                        .addOnFailureListener { error ->
                            Log.d("********", error.message.toString())
                        }


                }
            }

    }
    fun modifyUser(fname:String,lname:String,email: String, newPw:String, pNumber:String,address: String){
        if (fname.isNotEmpty() || lname.isNotEmpty() || pNumber.isNotEmpty()  || address.isNotEmpty()){
            var tempUserdata = userdata.value.toMutableMap()
            if (fname.isNotEmpty()){
                tempUserdata["firstName"] = fname
            }
            if (lname.isNotEmpty() ){
                tempUserdata["lastName"] = lname
            }
            if (pNumber.isNotEmpty() ){
                tempUserdata["phoneNumber"] = pNumber
            }
            if (address.isNotEmpty() ){
                tempUserdata["address"] = address
            }
            if (email.isNotEmpty()){
                fAuth
                    .currentUser
                    ?.updateEmail(email)

            }
            if (newPw.isNotEmpty()){
                fAuth
                    .currentUser
                    ?.updatePassword(newPw)
            }
            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .set(tempUserdata)
                .addOnSuccessListener {
                    Log.d("********", "Profile photos are being updated")

                }
                .addOnFailureListener { error ->
                    Log.d("********", error.message.toString())
                }

        }




    }


}

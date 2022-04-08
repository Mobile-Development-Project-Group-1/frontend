package com.example.mobile_development_project_group_1

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    private val storage    = Firebase.storage
    private val  ref     =  storage.reference
    var isAnyUser = mutableStateOf(false)
    var username= mutableStateOf("")
    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var userdata = mutableStateOf(mapOf<String,Any>())
    var isMapOpen = mutableStateOf(false)
    var publicPlaceData = mutableMapOf<String,Any>()
    var p_Url = mutableStateOf("")
    var tempListEvents = mutableListOf<Event>()

    fun disableDrawer() {
        isMapOpen.value = !isMapOpen.value
    }

    fun logInUser(email: String, pw: String, navController: NavHostController) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {

            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d("********", "Logged in successfully")
                    navController.navigate(HOME_ROUTE)
                    isAnyUser.value = true
                    errorMessage.value = ""
                    username.value= email

                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun signUpUser(firstName: String, lastName: String, address: String, phoneNumber: String, email: String, pw: String, pictureUrl: String, root: String, navController: NavHostController) {

        if (email.isNotEmpty() && pw.isNotEmpty()) {
            fAuth
                .createUserWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    isAnyUser.value = true
                    logInUser(email, pw, navController)
                    fireStore
                        .collection("users")
                        .document(it.user!!.uid)
                        .set( User(firstName, lastName, address, phoneNumber, pictureUrl, root) )
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
                .addOnFailureListener {
                    errorMessage.value = "Incorrect form of email or password"
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
    fun modifyUser(fname:String,lname:String, pNumber:String,address: String){
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
    fun modifyPassword(pw:String){
       if (pw.isNotEmpty()){
           fAuth
               .currentUser
               ?.updatePassword(pw)
       }
    }

    fun addThePublicPlaceImage(u: Uri){
          var tempRandonChar =('A'..'Z').random()
          var tempRandonNumber =(0..100).random()
        var path ="$tempRandonChar${ fAuth.currentUser?.uid.toString()}${tempRandonNumber}"
        ref.child(path).putFile(u)
            .addOnSuccessListener {
           val result = it.metadata!!.reference!!.downloadUrl;
           result.addOnSuccessListener { doc ->
               Log.d("................",doc.toString())
               var temp = doc.toString()
               publicPlaceData["pub_img_url"] = temp
               publicPlaceData["m_id"] = fAuth.currentUser!!.uid
               p_Url.value = temp
               Log.d("................",publicPlaceData.toString())
           }.addOnFailureListener {
               Log.d("................",errorMessage.toString())
           }

        }



    }
    fun changeImageState(){
       p_Url.value = ""
    }

    fun setPublicInfo(title:String,workdays:String,weekend:String,contactInfo:String,webLink:String,description:String){
        publicPlaceData["title"] = title
        publicPlaceData["workdays"]=workdays
        publicPlaceData["weekend"] = weekend
        publicPlaceData["contactUs"] =contactInfo
        publicPlaceData["weblink"] = webLink
        publicPlaceData["description"]=description
        Log.d("................",publicPlaceData.toString())


    }

    fun setAddressData(address: String,latitude:String,longitude:String){
        var tempLatitude = latitude.toDouble()
        var tempLongitude = longitude.toDouble()
        var tempGeoPoint  = GeoPoint( tempLatitude, tempLongitude )
        publicPlaceData["address"] =address
        publicPlaceData["coor"] = tempGeoPoint
        Log.d("................",publicPlaceData.toString())


    }
    fun setEventData(e_title:String,e_description:String,e_price:String,e_time:String,e_date:String){
        var event:Event = Event(e_title,e_description,e_price,e_time,e_date)
        tempListEvents.add(event)
        Log.d("................",tempListEvents.toString())

    }
    fun setAllPubData(){
          publicPlaceData["event"] = tempListEvents
          fireStore
              .collection("public_places")
              .add(publicPlaceData)
              .addOnSuccessListener {
                  tempListEvents.clear()
              }
        Log.d("................",tempListEvents.toString())
    }



}

package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePageView() {
   var isEdit :MutableState<Boolean> = remember {
       mutableStateOf(true)
   }
    
    if (isEdit.value){
       Surface() {
          Text(text = "Info-page")
        }
    }else{
        Surface() {
            Text(text = "Edit information page")
        }

    }
}
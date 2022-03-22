package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePageView() {
   var isEdit :MutableState<Boolean> = remember {
       mutableStateOf(true)
   }
    
    if (isEdit.value){
       Surface(modifier = Modifier.fillMaxSize(), color = Color.Green) {

        }
    }else{
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Red) {

        }

    }
}
package com.example.mobile_development_project_group_1


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Surface

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.unit.dp



@Composable
fun ProfilePageView() {
   var isEdit :MutableState<Boolean> = remember {
       mutableStateOf(true)
   }
    
    if (isEdit.value){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), horizontalAlignment =Alignment.CenterHorizontally ) {
         Surface(modifier = Modifier.width(295.dp).height(279.dp), shape = RoundedCornerShape(corner = CornerSize(15.dp)), color = Color.Black ) {

          }

        }
    }else{
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {

        }

    }
}
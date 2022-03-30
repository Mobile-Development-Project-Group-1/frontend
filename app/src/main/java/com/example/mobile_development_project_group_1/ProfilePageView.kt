package com.example.mobile_development_project_group_1


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

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
            Text(text = "Your Profile", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
         Surface(modifier = Modifier
             .width(295.dp)
             .height(400.dp).padding(0.dp,10.dp), shape = RoundedCornerShape(corner = CornerSize(15.dp)),  border = BorderStroke(2.dp, Color.LightGray)
         ) {

          }

        }
    }else{
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {

        }

    }
}
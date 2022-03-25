package com.example.mobile_development_project_group_1


import android.icu.text.CaseMap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Surface
import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ProfilePageView() {
   var isEdit :MutableState<Boolean> = remember {
       mutableStateOf(true)
   }
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    userVM.getUserData()
    if (isEdit.value){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), horizontalAlignment =Alignment.CenterHorizontally ) {
            Surface(modifier = Modifier
                .width(295.dp)
                .height(400.dp)
                .padding(0.dp, 10.dp), shape = RoundedCornerShape(corner = CornerSize(15.dp)),  border = BorderStroke(2.dp, Color(0xffED4956))
         ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier.size(80.dp), shape = CircleShape,
                        border = BorderStroke(width = 0.5.dp, Color(0xffED4956)),
                        elevation = 4.dp,
                    ) {

                    }
                }
                Divider(thickness = 1.dp, color = Color(0xffED4956))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState()),

                    )

                {
                    makeTextInfo("First name","Chen Pi")


                }



            }
          }

        }
    }else{
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {

        }

    }
}

@Composable
private fun makeTextInfo(title:String , data:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column()
        {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffED4956)
            )
            Text(text = data, fontSize = 12.sp)
        }

    }
    Divider(thickness = 1.dp)
}
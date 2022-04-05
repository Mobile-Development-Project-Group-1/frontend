package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun MakeOutlineButton(sValue: MutableState<String>, title:String, userData:String, keyboard: KeyboardType, img:Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        OutlinedTextField(value = sValue.value, onValueChange ={sValue.value =it} ,
            label = { Text(text = title) },
            placeholder = { Text(text = userData ) },
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    placeholderColor = Color.Gray,
                    trailingIconColor = Color(0xffed4956) ,
                    focusedLabelColor =  Color(0xffed4956),
                    focusedBorderColor = Color(0xffed4956),
                ),
            keyboardOptions = KeyboardOptions(keyboardType =keyboard ) ,
            trailingIcon = {
                Icon(painter = painterResource(id = img) , contentDescription ="" )
            },
        )

    }
    Divider(thickness = 1.dp)
}
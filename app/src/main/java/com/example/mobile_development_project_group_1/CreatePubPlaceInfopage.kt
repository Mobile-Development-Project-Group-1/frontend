package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun CreatePubPlaceInfo(nav: NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    var title  = remember {
        mutableStateOf("")
    }
    var workDays  = remember {
        mutableStateOf("")
    }
    var weekend  = remember {
        mutableStateOf("")
    }
    var contactInfo  = remember {
        mutableStateOf("")
    }
    var webLink  = remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally ) {
        Surface(modifier = Modifier
            .width(295.dp)
            .height(400.dp)
            .padding(0.dp, 10.dp), shape = RoundedCornerShape(corner = CornerSize(15.dp)),  border = BorderStroke(2.dp, Color(0xffED4956))
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(20.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    nav.navigate(PUB_PLACE_CREATION_ROUTE)

                                },
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Row(
                                modifier = Modifier.background(Color(0xffed4956)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_arrow_left),
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                        //.padding(20.dp),
                    ) {
                        Text(
                            text = " Your public place",
                            color = Color(0xffed4956),
                            fontWeight = FontWeight.Bold,
                            style =  MaterialTheme.typography.h6

                        )
                    }
                }
                Divider(thickness = 1.dp, color = Color(0xffED4956))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState()),

                    )

                {
                    MakeOutlineButtonForPublicPlace(title,"Public place name","bar",R.drawable.ic_title,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace( workDays,"Workdays","16-04",R.drawable.ic_workdays,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace(weekend,"Weekend","16-02",R.drawable.ic_workdays,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace(contactInfo,"Contact us","Email/Phone Number",
                        R.drawable.ic_contact,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace(webLink,"Web link","http//:www.bar.com/",
                        R.drawable.ic_baseline_link_24,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace(description,"Description","Describe your public place",
                        R.drawable.ic_baseline_link_24,
                        KeyboardType.Text)

                }



            }
        }
    }


}

@Composable
fun MakeOutlineButtonForPublicPlace(name:MutableState<String>,titleName:String,placeHolderTitle:String,iconImage:Int,keyboard: KeyboardType) {
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        OutlinedTextField(value = name.value, onValueChange ={name.value =it},
            label = { Text(text = titleName)},
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    placeholderColor = Color.Gray,
                    trailingIconColor = Color(0xffed4956) ,
                    focusedLabelColor =  Color(0xffed4956),
                    focusedBorderColor = Color(0xffed4956),
                ),
            keyboardOptions = KeyboardOptions(keyboardType = keyboard),
            placeholder = { Text(text = placeHolderTitle)},
            trailingIcon = { Icon(painter = painterResource(id =iconImage ) , contentDescription ="" )}
        )
        Divider(thickness = 1.dp)

    }

}


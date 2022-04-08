package com.example.mobile_development_project_group_1

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun PubAddresspage(nav:NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    val context = LocalContext.current
    var address  = remember {
        mutableStateOf("")
    }
    var latitude  = remember {
        mutableStateOf("")
    }

    var longitude = remember {
        mutableStateOf("")
    }
    var isClicked by remember {
        mutableStateOf(true)
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
                                    nav.navigate(PUB_PLACE_INFO_ROUTE)

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
                            text = " Your address",
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
                    MakeOutlineButtonForPublicPlace(address,"Address","xxxxStreet, 90570 Oulu",R.drawable.ic_address,
                        KeyboardType.Text)
                    MakeOutlineButtonForPublicPlace(latitude,"Latitude","65.01184918238897",R.drawable.ic_location,
                        KeyboardType.Number)
                    MakeOutlineButtonForPublicPlace(longitude,"Longitude","25.48109937249326",R.drawable.ic_location,
                        KeyboardType.Number)

                }



            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment =Alignment.CenterVertically )
        {

            if (isClicked){
                OutlinedButton(
                    onClick = {
                        if (address.value.isNotEmpty() && latitude.value.isNotEmpty() && longitude.value.isNotEmpty()){
                            userVM.setAddressData(address.value,latitude.value,longitude.value)
                            isClicked =! isClicked
                        }else{
                            Toast.makeText(
                                context,
                                "Please fill in the full address details",
                                Toast.LENGTH_SHORT
                            ).show()

                        }


                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "Confirm",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }else{
                OutlinedButton(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Add event detail",
                            Toast.LENGTH_SHORT
                        ).show()
                        nav.navigate(PUB_CREATE_EVENT_ROUTE)

                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "Next",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }


}
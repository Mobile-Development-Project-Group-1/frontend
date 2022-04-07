package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
fun  PubEventPage(nav:NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    val e_title = remember{
        mutableStateOf("")
    }
    val e_description = remember{
        mutableStateOf("")
    }
    val e_price = remember{
        mutableStateOf("")
    }
    val e_time = remember{
        mutableStateOf("")
    }
    val e_date = remember{
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
                                    nav.navigate(PUB_CREATE_Address_ROUTE)

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
                            text = " Add your event",
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
                  MakeOutlineButtonForPublicPlace(
                      name =e_title ,
                      titleName ="Event name" ,
                      placeHolderTitle = "xxxx",
                      iconImage =R.drawable.ic_event ,
                      keyboard = KeyboardType.Text
                  )
                    MakeOutlineButtonForPublicPlace(
                        name =e_description ,
                        titleName ="Description" ,
                        placeHolderTitle = "xxxx",
                        iconImage =R.drawable.ic_description ,
                        keyboard = KeyboardType.Text
                    )
                    MakeOutlineButtonForPublicPlace(
                        name =e_price ,
                        titleName ="Price" ,
                        placeHolderTitle = "xx€",
                        iconImage =R.drawable.ic_euro ,
                        keyboard = KeyboardType.Text
                    )
                    MakeOutlineButtonForPublicPlace(
                        name =e_time ,
                        titleName ="Time" ,
                        placeHolderTitle = "17-23",
                        iconImage =R.drawable.ic_euro ,
                        keyboard = KeyboardType.Text
                    )
                    MakeOutlineButtonForPublicPlace(
                        name =e_date ,
                        titleName ="date" ,
                        placeHolderTitle = "04/07/2022",
                        iconImage =R.drawable.ic_euro ,
                        keyboard = KeyboardType.Text
                    )
                }



            }
        }
    }
}
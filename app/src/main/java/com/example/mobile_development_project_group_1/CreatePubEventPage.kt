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
fun  PubEventPage(nav:NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    val context = LocalContext.current
    val evenCount = remember{
        mutableStateOf(0)
    }

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
    var isClicked by remember {
        mutableStateOf(false)
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
                        keyboard = KeyboardType.Number
                    )
                    MakeOutlineButtonForPublicPlace(
                        name =e_time ,
                        titleName ="Time" ,
                        placeHolderTitle = "17-23",
                        iconImage =R.drawable.ic_workdays ,
                        keyboard = KeyboardType.Number
                    )
                    MakeOutlineButtonForPublicPlace(
                        name =e_date ,
                        titleName ="date" ,
                        placeHolderTitle = "04/07/2022",
                        iconImage =R.drawable.ic_baseline_calendar_month_24 ,
                        keyboard = KeyboardType.Text
                    )
                }



            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(

                onClick = {
                    if (e_title.value.isNotEmpty()&& e_description.value.isNotEmpty()&& e_price.value.isNotEmpty()
                        && e_time.value.isNotEmpty() && e_date.value.isNotEmpty()){
                        userVM.setEventData(e_title.value,e_description.value,e_price.value,e_time.value,e_date.value)
                        evenCount.value+=1
                        isClicked = true
                        Toast.makeText(
                            context,
                            "Added ${evenCount.value} event",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "Please fill in the complete event information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }



                },
                colors = ButtonDefaults
                    .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
            ) {
                Text(
                    text = "Add",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
           if(isClicked){
               OutlinedButton(
                   onClick = {
                       Toast.makeText(
                           context,
                           "Your information was successfully sent",
                           Toast.LENGTH_SHORT
                       ).show()

                       nav.navigate(HOME_ROUTE)

                   },
                   colors = ButtonDefaults
                       .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
               ) {
                   Text(
                       text = "Finished",
                       fontSize = 14.sp,
                       fontWeight = FontWeight.Bold
                   )
               }
           }

        }
    }

}
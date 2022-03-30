package com.example.mobile_development_project_group_1

import android.widget.Toast
import androidx.compose.foundation.*

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
fun ProfileMOView(nav: NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    val context = LocalContext.current

    var fname = remember { mutableStateOf("") }
    var lname = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var newPw = remember { mutableStateOf("") }
    var pNumber = remember { mutableStateOf("") }
    var address = remember { mutableStateOf("") }

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
                                    nav.navigate(PROFILE_ROUTE)
                                    Toast
                                        .makeText(
                                            context,
                                            "Your profile ",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
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
                            text = "Your Profile",
                            color = Color(0xffed4956),
                            fontWeight = FontWeight.Bold,
                            style =  MaterialTheme.typography.h5

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

                    makeOutlineButton()
                }



            }
        }
      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceEvenly) {
          OutlinedButton(
              onClick = {
                  nav.navigate(PROFILE_ROUTE )
                  Toast.makeText(
                      context,
                      "Your profile has been updated",
                      Toast.LENGTH_SHORT
                  ).show()

              },
              colors = ButtonDefaults
                  .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
          ) {
              Text(
                  text = "Modify",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
              )
          }

          OutlinedButton(
              onClick = {
                  userVM.deleteUser()
                  nav.navigate(HOME_ROUTE)
                  Toast.makeText(
                      context,
                      "You have deleted your account",
                      Toast.LENGTH_SHORT
                  ).show()
              },
              colors = ButtonDefaults
                  .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
          ) {
              Text(
                  text = "Delete account",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
              )
          }
      }

    }

}
@Composable
fun makeOutlineButton(sValue:MutableState<String>,title:String,userData:String, keyboard: KeyboardType,img:Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        OutlinedTextField(value = sValue.value, onValueChange ={sValue.value =it} ,
            label = { Text(text = title) },
            placeholder = { Text(text = userData )},
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
}



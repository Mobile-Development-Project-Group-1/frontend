package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginView(userVM: UserViewModel, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var isLoginOpen by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row( // 1
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.background(Color(0xffed4956))
            ) {
                Logo(R.drawable.ic_logo)
            }
        }
        
        if (isLoginOpen) {
            Row( // 2
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDF09B))
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffed4956)
                )
            }

            Row( // 3
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Card(
                        shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                        border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                        elevation = 10.dp
                    ) {
                        Column {
                            TextField(
                                value = email,
                                onValueChange = { email = it },
                                placeholder = { Text(text = "Email") },
                                colors = TextFieldDefaults
                                    .textFieldColors(
                                        backgroundColor = Color.White,
                                        textColor = Color(0xffed4956),
                                        placeholderColor = Color(0xffed4956)
                                    ),
                                singleLine = true
                            )
                            TextField(
                                value = pw,
                                onValueChange = { pw = it },
                                placeholder = { Text(text = "Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                colors = TextFieldDefaults
                                    .textFieldColors(
                                        backgroundColor = Color.White,
                                        textColor = Color(0xffed4956),
                                        placeholderColor = Color(0xffed4956)
                                    ),
                                singleLine = true
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(52.dp)
                            .clickable {
                                userVM.loginUser(email, pw)
                                navController.navigate(HOME_ROUTE)
                            },
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.background(Color(0xffed4956)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        } else {
            Row( // 2
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDF09B))
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Register",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffed4956)
                )
            }

            Column( // 3
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Magenta),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                ) { // INPUTS
                    Card(
                        shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                        border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                        elevation = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.background(Color.Red)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .background(Color.Gray)
                            ) {
                                TextField(
                                    value = firstName,
                                    onValueChange = { firstName = it },
                                    placeholder = { Text(text = "First name") },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                TextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    placeholder = { Text(text = "Email") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                TextField(
                                    value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    placeholder = { Text(text = "Phone number") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(1f)
                            ) {
                                TextField(
                                    value = lastName,
                                    onValueChange = { lastName = it },
                                    placeholder = { Text(text = "Last name") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                TextField(
                                    value = pw,
                                    onValueChange = { pw = it },
                                    placeholder = { Text(text = "Password") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                TextField(
                                    value = address,
                                    onValueChange = { address = it },
                                    placeholder = { Text(text = "Address") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
                Row( // BUTTON
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(52.dp)
                            .clickable {
                                //userVM.loginUser(email, pw)
                                //navController.navigate(HOME_ROUTE)
                            },
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.background(Color(0xffed4956)),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
        
        Row( // 4
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                elevation = 10.dp,
                modifier = Modifier
                    .clickable {
                        isLoginOpen = true
                    }
            ) {
                Row(
                    modifier = Modifier
                        .background(if (isLoginOpen) Color(0xffed4956) else Color.White)
                ) {
                    Text(
                        text = "Login",
                        modifier = Modifier.padding(20.dp, 10.dp),
                        color = if (isLoginOpen) Color.White else Color(0xffed4956),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
            Card(
                shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                elevation = 10.dp,
                modifier = Modifier
                    .clickable {
                        isLoginOpen = false
                    }
            ) {
                Row(
                    modifier = Modifier
                        .background(if (isLoginOpen) Color.White else Color(0xffed4956))
                ) {
                    Text(
                        text = "Register",
                        modifier = Modifier.padding(20.dp, 10.dp),
                        color = if (isLoginOpen) Color(0xffed4956) else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }


//    OutlinedTextField(
//        value = email,
//        onValueChange = { email = it },
//        label = { Text(text = "Email") }
//    )
//    OutlinedTextField(
//        value = pw,
//        onValueChange = { pw = it },
//        label = { Text(text = "Password") },
//        visualTransformation = PasswordVisualTransformation()
//    )
//    OutlinedButton(
//        onClick = {
//            userVM.loginUser(email, pw)
//            navController.navigate(HOME_ROUTE)
//        },
//        modifier = Modifier
//            .padding(10.dp)
//    ) {
//        Text(text = "Login")
//    }
}

@Composable
fun Logo(resId: Int) {
    Image(painter = painterResource(resId), contentDescription = "")
}


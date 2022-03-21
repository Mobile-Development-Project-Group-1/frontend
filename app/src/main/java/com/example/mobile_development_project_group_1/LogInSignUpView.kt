package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var route by remember { mutableStateOf("") }

    var isLoginOpen by remember { mutableStateOf(true) }
    var isUser by remember { mutableStateOf(true) }
    var isManager by remember { mutableStateOf(false) }

    route = when(isUser) {
        true -> "USER"
        false -> "MANAGER"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row( // 1
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
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
                                userVM.logInUser(email, pw)
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
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp)
                ) { // INPUTS
                    Card(
                        shape = RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp),
                        border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
                        elevation = 10.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(168.dp)
                    ) {
                        Row {
                            Column(
                                modifier = Modifier.fillMaxWidth(0.5f)
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
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )
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
                                    singleLine = true,
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                                )
                                TextField(
                                    value = phoneNumber,
                                    onValueChange = { phoneNumber = it },
                                    placeholder = { Text(text = "Phone number") },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(168.dp)
                                    .background(Color(0xFF8B8B8B))
                            ) {

                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(1f)
                            ) {
                                TextField(
                                    value = lastName,
                                    onValueChange = { lastName = it },
                                    placeholder = { Text(text = "Last name") },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                                )
                                TextField(
                                    value = address,
                                    onValueChange = { address = it },
                                    placeholder = { Text(text = "Address") },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                )
                            }
                        }
                    }
                }
                Row( // BUTTON
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 20.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isUser,
                            onCheckedChange = {
                                isUser = !isUser
                                isManager = !isManager
                            }
                        )
                        Text(text = "User")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isManager,
                            onCheckedChange = {
                                isManager = !isManager
                                isUser = !isUser
                            }
                        )
                        Text(text = "Manager")
                    }
                    Card(
                        modifier = Modifier
                            .size(52.dp)
                            .clickable {
                                userVM.signUpUser(email, pw, firstName, lastName, address, phoneNumber, route)
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
                                painter = painterResource(R.drawable.ic_check),
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
                        .width(110.dp)
                        .background(if (isLoginOpen) Color(0xffed4956) else Color.White),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Login",
                        color = if (isLoginOpen) Color.White else Color(0xffed4956),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(0.dp, 10.dp)
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
                        .width(110.dp)
                        .background(if (isLoginOpen) Color.White else Color(0xffed4956)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Register",
                        color = if (isLoginOpen) Color(0xffed4956) else Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(0.dp, 10.dp)
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


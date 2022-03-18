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
import androidx.compose.ui.graphics.Shape
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

    Column( // Main Column
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row( // Logo
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
            Row( // Login Title
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

            Row( // Login Fields
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
                    ConfirmButton(
                        functionality = {
                            userVM.logInUser(email, pw)
                            navController.navigate(HOME_ROUTE)
                        },
                        resId = R.drawable.ic_arrow_right
                    )
                }
            } // Login Fields
            Row( // Register Switch Button
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 150.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                SwitchButton(
                    shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                    functionality = { isLoginOpen = false },
                    text = "Register"
                )
            }
        } else {
            Row( // Register Title
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

            Column( // Register Fields
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
                Row( // Buttons
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
                    ConfirmButton(
                        functionality = {
                            userVM.signUpUser(
                                email,
                                pw,
                                firstName,
                                lastName,
                                address,
                                phoneNumber,
                                route
                            )
                            navController.navigate(HOME_ROUTE)
                        },
                        resId = R.drawable.ic_check
                    )
                } // Buttons
            } // Register Fields
            Row( // Login Switch Button
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SwitchButton(
                    shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp),
                    functionality = { isLoginOpen = true },
                    text = "Login"
                )
            }
        } // else
    } // Main Column
} // LoginView

@Composable
fun Logo(resId: Int) {
    Image(painter = painterResource(resId), contentDescription = "")
}

@Composable
fun ConfirmButton(functionality: Any, resId: Int) {
    Card(
        modifier = Modifier
            .size(52.dp)
            .clickable {
                functionality
            },
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = Modifier.background(Color(0xffed4956)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(resId),
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SwitchButton(shape: Shape, functionality: Any, text: String) {
    Card(
        shape = shape,
        border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
        elevation = 10.dp,
        modifier = Modifier
            .clickable {
                functionality
            }
    ) {
        Row(
            modifier = Modifier
                .width(110.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = Color(0xffed4956) ,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
        }
    }
}


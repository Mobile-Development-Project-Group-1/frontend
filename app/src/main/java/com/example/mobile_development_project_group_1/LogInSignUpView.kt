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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController

@Composable
fun LoginView(userVM: UserViewModel, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var root by remember { mutableStateOf("") }
    var pictureUrl by remember { mutableStateOf("https://firebasestorage.googleapis.com/v0/b/mdp-firebase-g1.appspot.com/o/img.png?alt=media&token=7a17860b-7342-4c81-b1db-b26bb15892a8") }

    var isLoginOpen by remember { mutableStateOf(true) }
    var isHiddenPw by remember { mutableStateOf(true) }
    var isUser by remember { mutableStateOf(true) }
    var isManager by remember { mutableStateOf(false) }

    root = when(isUser) {
        true -> "USER"
        false -> "MANAGER"
    }

    Column( // Main Column
        modifier = Modifier
            .fillMaxSize()
    ) {

        Logo(R.drawable.ic_logo, navController)

        if (isLoginOpen) {
            Title("Login")

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
                            InputField(
                                title = email,
                                onTitleChange = { email = it },
                                text = { Text(text = "Email") },
                                visTrans = VisualTransformation.None,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                leadingIcon = { IconHolder(R.drawable.ic_email) },
                                //trailingIcon = null
                            )
                            TextField(
                                value = pw,
                                onValueChange = { pw = it },
                                placeholder = { Text(text = "Password") },
                                visualTransformation = if (isHiddenPw) { PasswordVisualTransformation() } else { VisualTransformation.None },
                                colors = TextFieldDefaults
                                    .textFieldColors(
                                        backgroundColor = Color.White,
                                        textColor = Color(0xffed4956),
                                        placeholderColor = Color(0xffed4956)
                                    ),
                                singleLine = true,
                                shape = MaterialTheme
                                    .shapes.small.copy(ZeroCornerSize),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                leadingIcon = { IconHolder(R.drawable.ic_lock) },
                                trailingIcon = {
                                    Icon(
                                        painter = painterResource(
                                            if (isHiddenPw) {
                                                R.drawable.ic_open_eye
                                            } else {
                                                R.drawable.ic_closed_eye
                                            }
                                        ),
                                        contentDescription = "",
                                        modifier = Modifier.clickable {
                                            isHiddenPw = !isHiddenPw
                                        },
                                        tint = Color(0xffed4956)
                                    )
                                }
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
                            userVM.logInUser(email, pw, navController)
                        },
                        resId = R.drawable.ic_arrow_right
                    )
                }
            } // Login Fields
            ErrorMessage(userVM)
            Row( // Register Switch Button
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 116.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                SwitchButton(
                    shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp),
                    functionality = {
                        isLoginOpen = !isLoginOpen
                        email = ""
                        pw = ""
                        userVM.errorMessage.value = ""
                    },
                    text = "Register"
                )
            }
        } else {
            Title("Register")

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
                                InputField(
                                    title = firstName,
                                    onTitleChange = { firstName = it },
                                    text = { Text(text = "First name") },
                                    visTrans = VisualTransformation.None,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    leadingIcon = { IconHolder(R.drawable.ic_account) },
                                    //trailingIcon = null
                                )
                                InputField(
                                    title = email,
                                    onTitleChange = { email = it },
                                    text = { Text(text = "Email") },
                                    visTrans = VisualTransformation.None,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    leadingIcon = { IconHolder(R.drawable.ic_email) },
                                    //trailingIcon = null
                                )
                                InputField(
                                    title = phoneNumber,
                                    onTitleChange = { phoneNumber = it },
                                    text = { Text(text = "Phone number") },
                                    visTrans = VisualTransformation.None,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                    leadingIcon = { IconHolder(R.drawable.ic_phone) },
                                    //trailingIcon = null
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
                                InputField(
                                    title = lastName,
                                    onTitleChange = { lastName = it },
                                    text = { Text(text = "Last name") },
                                    visTrans = VisualTransformation.None,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    leadingIcon = { IconHolder(R.drawable.ic_account) },
                                    //trailingIcon = null
                                )
                                TextField(
                                    value = pw,
                                    onValueChange = { pw = it },
                                    placeholder = { Text(text = "Password") },
                                    visualTransformation = if (isHiddenPw) { PasswordVisualTransformation() } else { VisualTransformation.None },
                                    colors = TextFieldDefaults
                                        .textFieldColors(
                                            backgroundColor = Color.White,
                                            textColor = Color(0xffed4956),
                                            placeholderColor = Color(0xffed4956)
                                        ),
                                    singleLine = true,
                                    shape = MaterialTheme
                                        .shapes.small.copy(ZeroCornerSize),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    leadingIcon = { IconHolder(R.drawable.ic_lock) },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(
                                                if (isHiddenPw) {
                                                    R.drawable.ic_open_eye
                                                } else {
                                                    R.drawable.ic_closed_eye
                                                }
                                            ),
                                            contentDescription = "",
                                            modifier = Modifier.clickable {
                                                isHiddenPw = !isHiddenPw
                                            },
                                            tint = Color(0xffed4956)
                                        )
                                    }
                                )
                                InputField(
                                    title = address,
                                    onTitleChange = { address = it },
                                    text = { Text(text = "Address") },
                                    visTrans = VisualTransformation.None,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    leadingIcon = { IconHolder(R.drawable.ic_address) },
                                    //trailingIcon = null
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
                                email = email,
                                pw = pw,
                                firstName = firstName,
                                lastName = lastName,
                                address = address,
                                phoneNumber = phoneNumber,
                                root = root,
                                navController = navController,
                                pictureUrl = pictureUrl
                            )
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
                    functionality = {
                        isLoginOpen = !isLoginOpen
                        email = ""
                        pw = ""
                        firstName = ""
                        lastName = ""
                        phoneNumber =""
                        address = ""
                        userVM.errorMessage.value = ""
                    },
                    text = "Login"
                )
            }
            ErrorMessage(userVM)
        } // else
    } // Main Column
} // LoginView

@Composable
fun ErrorMessage(userVM: UserViewModel) {
    if (userVM.errorMessage.value.isEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ) {}
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = userVM.errorMessage.value,
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
        }
    }
}

@Composable
fun Logo(resId: Int, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.32f)
                .padding(20.dp),
        ) {
            Card(
                modifier = Modifier
                    .size(42.dp)
                    .clickable {
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
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Box(
                modifier = Modifier.background(Color(0xffed4956))
            ) {
                Image(painter = painterResource(resId), contentDescription = "")
            }
        }
    }
}

@Composable
fun ConfirmButton(functionality: () -> Unit, resId: Int) {
    Card(
        modifier = Modifier
            .size(52.dp)
            .clickable {
                functionality()
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
fun SwitchButton(shape: Shape, functionality: () -> Unit, text: String) {
    Card(
        shape = shape,
        border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
        elevation = 10.dp,
        modifier = Modifier
            .clickable {
                functionality()
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

@Composable
fun IconHolder(resId: Int) {
    Icon(
        painter = painterResource(resId),
        contentDescription = "",
        tint = Color(0xffed4956)
    )
}

@Composable
fun InputField(
    title: String,
    onTitleChange: (String) -> Unit,
    text: @Composable (() -> Unit)?,
    visTrans: VisualTransformation,
    keyboardOptions: KeyboardOptions,
    leadingIcon: @Composable (() -> Unit)?
) {
    TextField(
        value = title,
        onValueChange = onTitleChange,
        placeholder = text,
        visualTransformation = visTrans,
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = Color.White,
                textColor = Color(0xffed4956),
                placeholderColor = Color(0xffed4956)
            ),
        singleLine = true,
        shape = MaterialTheme
            .shapes.small.copy(ZeroCornerSize),
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon
    )
}

@Composable
fun Title(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xffed4956)
        )
    }
}


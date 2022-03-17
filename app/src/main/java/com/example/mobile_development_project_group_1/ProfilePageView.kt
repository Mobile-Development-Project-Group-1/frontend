package com.example.mobile_development_project_group_1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePageView(navController: NavHostController) {
    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth

    var currentUserFirstName by remember { mutableStateOf("") }
    var currentUserLastName by remember { mutableStateOf("") }
    var currentUserAddress by remember { mutableStateOf("") }
    var currentUserPhoneNumber by remember { mutableStateOf("") }
    var currentUserNewPassword by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserFirstName = it.get("firstName").toString()
            currentUserLastName = it.get("lastName").toString()
            currentUserAddress = it.get("address").toString()
            currentUserPhoneNumber = it.get("phoneNumber").toString()
        }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
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
            modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile page",
                color = Color(0xffed4956),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Card(
            shape = RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp),
            border = BorderStroke(0.5.dp, Color(0xffEBEBEB)),
            elevation = 10.dp
        ) {
            Column {
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    placeholder = { Text(text = currentUserFirstName) },
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color(0xffed4956),
                            placeholderColor = Color(0xffed4956)
                        ),
                    singleLine = true
                )
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = { Text(text = currentUserLastName) },
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color(0xffed4956),
                            placeholderColor = Color(0xffed4956)
                        ),
                    singleLine = true
                )
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    placeholder = { Text(text = currentUserAddress) },
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color(0xffed4956),
                            placeholderColor = Color(0xffed4956)
                        ),
                    singleLine = true
                )
                TextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    placeholder = { Text(text = currentUserPhoneNumber) },
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color(0xffed4956),
                            placeholderColor = Color(0xffed4956)
                        ),
                    singleLine = true
                )
                TextField(
                    value = currentUserNewPassword,
                    onValueChange = { currentUserNewPassword = it },
                    placeholder = { Text(text = "New password") },
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .clickable {
                        //userVM.logInUser(email, pw)
                    },
                shape = RoundedCornerShape(30.dp)
            ) {
                Row(
                    modifier = Modifier
                        .background(Color(0xffed4956))
                        .padding(20.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Modify",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
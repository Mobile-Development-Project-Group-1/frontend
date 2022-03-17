package com.example.mobile_development_project_group_1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginView(userVM: UserViewModel, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )
        OutlinedTextField(
            value = pw,
            onValueChange = { pw = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedButton(
            onClick = {
                userVM.loginUser(email, pw)
                navController.navigate(HOME_ROUTE)
            },
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(text = "Login")
        }
    }
}
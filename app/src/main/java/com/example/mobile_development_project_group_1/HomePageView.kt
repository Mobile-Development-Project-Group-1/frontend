package com.example.mobile_development_project_group_1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@ExperimentalFoundationApi
@Composable
fun HomeView(navController: NavHostController) {
    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth
    var currentUserRoute by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserRoute = it.get("root").toString()
        }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.914f),
        contentPadding = PaddingValues(16.dp, 0.dp)
    ) {
        items(15) {
            Card(
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .padding(16.dp, 16.dp)
                    .width(140.dp)
                    .height(150.dp),
                border = BorderStroke(2.dp, Color(0xffed4956))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "BAR")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 80.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (
                currentUserRoute == "USER"
                || currentUserRoute == "MANAGER"
                || currentUserRoute == "ADMIN"
            ) {
                Card(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            //navController.navigate(HOME_ROUTE)
                        },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xffed4956)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
            Divider(modifier = Modifier.width(0.1.dp).height(10.dp).background(Color.Transparent))
            if (currentUserRoute == "MANAGER" || currentUserRoute == "ADMIN") {
                Card(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            //navController.navigate(HOME_ROUTE)
                        },
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xffed4956)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_create_new),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                } // "Add new" button
            } // IF
        } // Buttons
    } // Column for "Add new" button
} // HomeView()

//        Text(text = "Main page")
//        when (currentUserRoute) {
//            ADMIN_ROOT -> Text(text = "You are $currentUserRoute")
//            MANAGER_ROOT -> Text(text = "You are $currentUserRoute")
//            USER_ROOT -> Text(text = "You are $currentUserRoute")
//        }
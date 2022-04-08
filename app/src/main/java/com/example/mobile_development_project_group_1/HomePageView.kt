package com.example.mobile_development_project_group_1

import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@ExperimentalFoundationApi
@Composable
fun HomeView(navController: NavHostController) {

    val fAuth = Firebase.auth
    val fireStore = Firebase.firestore
    var currentUserRoute by remember { mutableStateOf("") }
    var currentPubPlaceId by remember { mutableStateOf("") }
    var isPubOpen by remember { mutableStateOf(false) }
    val pubPlaceVM = viewModel<PubPlaceViewModel>(LocalContext.current as ComponentActivity)
     pubPlaceVM.getPubPlaceInfo()


    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserRoute = it.get("root").toString()
        }

    if (!isPubOpen) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.914f),
            contentPadding = PaddingValues(16.dp, 0.dp)
        ) {
            pubPlaceVM.pubPlaceLocations.forEach { elem ->
                item {
                    Card(
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier
                            .padding(16.dp, 16.dp)
                            .width(140.dp)
                            .height(150.dp)
                            .clickable {
                                currentPubPlaceId = elem.key
                                isPubOpen = !isPubOpen
                            },
                        border = BorderStroke(2.dp, Color(0xffed4956))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = elem.value.title)
                        }
                    } // Card
                } // item
            } // forEach
        } // LazyVerticalGrid
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
                                navController.navigate(CHAT_ROUTE)
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
                Divider(modifier = Modifier
                    .width(0.1.dp)
                    .height(10.dp)
                    .background(Color.Transparent))
                if (currentUserRoute == "MANAGER" || currentUserRoute == "ADMIN") {
                    Card(
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.navigate(PUB_PLACE_CREATION_ROUTE)
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
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Card(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            isPubOpen = !isPubOpen
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
            } // Back to HomePage button
            Column() {
                Text(text = currentPubPlaceId)
                Text(text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.description)
            }
        }
    }
} // HomeView()



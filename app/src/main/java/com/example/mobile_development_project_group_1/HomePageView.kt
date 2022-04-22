package com.example.mobile_development_project_group_1


import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
    var isDescriptionOpen by remember { mutableStateOf(false) }

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
                                .fillMaxSize()
                                .background(Color(0xffed4956)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.8f)
                            ) {
                                AsyncImage(
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                    model = elem.value.pub_img_url, contentDescription = ""
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(1f),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = CenterVertically
                            ) {
                                Text(
                                    text = elem.value.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Visible
                                    

                                )
                            }
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

            ) {
                Card(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            isPubOpen = !isPubOpen
                            isDescriptionOpen = false
                        },
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Row(
                        modifier = Modifier.background(Color(0xffed4956)),

                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_left),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        .paddingFromBaseline(0.dp, 10.dp),
                    text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.title,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp
                )
            } // Back to HomePage button
            Column(
                modifier = Modifier

                    .fillMaxWidth()
                    .fillMaxHeight(0.95f)
                    .padding(10.dp)
            ) {
                Column {
                    Row {
                        Row(
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(200.dp)
                                .height(150.dp),
                        ) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                model = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.pub_img_url,
                                contentDescription = "image",
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(3.dp)
                                .width(200.dp)
                                .height(150.dp)
                                .background(
                                    color = Color(0xffed4956),
                                    shape = RoundedCornerShape(50.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly,

                        ) {

                            Text(
                                fontSize = 12.sp,
                                text = "Opening hours \n ${pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.workdays}",
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                fontSize = 12.sp,
                                text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.address,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                fontSize = 12.sp,
                                text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.contactUs,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                fontSize = 12.sp,
                                text =  pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.weblink,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(0.dp, 10.dp)
                                .fillMaxWidth()
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(50.dp)
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                modifier = Modifier
                                    .border(
                                        1.dp,
                                        Color(0xffed4956),
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .clickable {
                                        isDescriptionOpen = !isDescriptionOpen
                                    }
                                    .fillMaxWidth(),
                                text = "Description",
                                color = Color(0xffed4956),
                                textAlign = TextAlign.Center
                            )
                            if (isDescriptionOpen) {
                                Text(
                                    modifier = Modifier.padding(0.dp, 10.dp),
                                    text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.description
                                )
                            }
                        }
                        pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.event.forEach { event ->

                            Column(
                                modifier = Modifier
                                    .border(1.dp, Color(0xffed4956))
                                    .padding(0.dp, 5.dp)
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(50.dp),
                                    )
                                ,
                                verticalArrangement = Arrangement.SpaceBetween

                            ) {
                                Row(
                                    verticalAlignment = CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)



                                ) {

                                    Column(modifier = Modifier.width(70.dp)) {

                                        Text(
                                            text = event["e_date"].toString(),
                                            textAlign = TextAlign.Center,
                                            maxLines = 1,
                                            fontSize = 13.sp,
                                            fontFamily = SansSerif
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = event["e_title"].toString(),
                                            textDecoration = TextDecoration.Underline,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Column(modifier = Modifier.width(150.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally) {

                                            Text(
                                                fontSize = 14.sp,
                                                text = event["e_description"].toString(),
                                                textAlign = TextAlign.Center,
                                                maxLines = 20,

                                            )
                                        }
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceAround,
                                        modifier = Modifier.width(60.dp)
                                    ) {
                                        Text(
                                            text = event["e_time"].toString(),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = "${event["e_price"].toString()} €",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                        )

                                    }
                                }
                            }
                            Divider(thickness = 10.dp , color = Color.White)
                        } // forEach
                    }

                }
            }
        }
    }
} // HomeView()



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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
    val scrollState = rememberScrollState()
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
                                    fontSize = 18.sp
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
            Column(
                modifier = Modifier

                    .fillMaxWidth()
                    .fillMaxHeight(0.95f)
                    .padding(10.dp)
            ) {
                Column() {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround,


                        ) {
                        Text(
                            modifier = Modifier.paddingFromBaseline(0.dp,10.dp),
                            text = pubPlaceVM.pubPlaceLocations[currentPubPlaceId]!!.title,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp
                        )
                    }
                    Row() {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .width(200.dp)
                                .height(150.dp)
                                .border(1.dp, Color(0xffed4956)),

                            painter = painterResource(R.drawable.ic_bar),
                            contentDescription = "image",
                        )
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
                            verticalArrangement = Arrangement.SpaceAround

                        ) {

                            Text(
                                text = "Opening hours",
                                color = Color.White
                            )
                            Text(
                                text = "Address",
                                color = Color.White
                            )
                            Text(
                                text = "Contact us",
                                color = Color.White
                            )
                            Text(
                                text = "Web link",
                                color = Color.White
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .verticalScroll(state = scrollState)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(3.dp)
                                .fillMaxWidth()

                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(50.dp)
                                )
//                            .border(1.dp,
//                                Color(0xffed4956),
//                                shape = RoundedCornerShape(50.dp)),
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally

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
                                    text = "This is an amazing bar. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
                                )
                            }
                        }
                        repeat(10) {
                            Column(
                                modifier = Modifier
                                    .border(1.dp, Color(0xffed4956))
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(50.dp),
                                    )
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.SpaceBetween

                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween

                                ) {

                                    Column(modifier = Modifier.width(70.dp)) {

                                        Text(
                                            "22/10/2022",
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
                                            text = "Event 1",
                                            textDecoration = TextDecoration.Underline,
                                            fontWeight = FontWeight.Bold
                                        )

                                        Column(modifier = Modifier.width(250.dp)) {

                                            Text(
                                                "This is an amazing bar. Lorem Ipsum is simply dummy text of the printing and typesett",
                                                textAlign = TextAlign.Center,
                                                maxLines = 2,
//                                    overflow = TextOverflow.Visible
                                            )
                                        }
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.SpaceAround,
                                        modifier = Modifier.width(60.dp)
                                    ) {
                                        Text(
                                            text = "17-20",
                                            textAlign = TextAlign.Center
                                        )
                                        Column() {

                                            Text(
                                                "10$",
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
} // HomeView()



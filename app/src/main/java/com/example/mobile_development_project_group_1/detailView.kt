package com.example.mobile_development_project_group_1

import android.graphics.fonts.FontFamily
import android.widget.ScrollView
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DetailView() {
    val scrollState = rememberScrollState()
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
                    .size(42.dp)
                    .clickable {

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
            Column() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,


                ) {
                    Text(
                        modifier = Modifier.paddingFromBaseline(0.dp,10.dp),
                        text = "Bar name",
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
                                .fillMaxWidth(),
                            text = "Description",
                            color = Color(0xffed4956),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "This is an amazing bar. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
                        )
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
                                        fontFamily = SansSerif,

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

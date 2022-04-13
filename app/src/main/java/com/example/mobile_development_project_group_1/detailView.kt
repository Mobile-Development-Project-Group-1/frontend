package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp

@Composable
fun DetailView() {
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
                Row() {
                  Column(
                      modifier = Modifier
                          .width(200.dp)
                          .height(100.dp)
                          .border(1.dp, Color(0xffed4956), RoundedCornerShape(50.dp))
                  ){
                      Image(
                          modifier = Modifier
                              .fillMaxSize()
                              .clip(RoundedCornerShape(10)),

                                  painter = painterResource(R.drawable.ic_bar),
                          contentDescription = "image",
                      )
                  }
               Column (
                   modifier = Modifier
                       .padding(3.dp)
                       .width(200.dp)
                       .height(100.dp)
                       .background(
                           color = Color(0xffed4956),
                           shape = RoundedCornerShape(50.dp)),
                   horizontalAlignment = Alignment.CenterHorizontally

                       ){

                   Text(text = "Opening hours")
                   Text(text = "Address")
                   Text(text = "Contact us")
                   Text(text = "Web link")
               }

                }
                Column (
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xffed4956),
                            shape = RoundedCornerShape(50.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ){

                    Text(text = "Description")
                }
            }
        }
    }
}
package com.example.mobile_development_project_group_1

import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun AddNewPubPlaceView(navController: NavHostController) {
    val context = LocalContext.current
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    var imgUrl by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){

        imgUrl = it
    }
    imgUrl?.let {

        userVM.addThePublicPlaceImage(imgUrl!!)

    }

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
        } // Back to HomePage button
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally ) {
            Surface(modifier = Modifier
                .width(295.dp)
                .height(400.dp)
                .padding(0.dp, 10.dp), shape = RoundedCornerShape(corner = CornerSize(15.dp)),  border = BorderStroke(2.dp, Color(0xffED4956))
            ) {

                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                                horizontalAlignment = Alignment.CenterHorizontally


                        ) {
                            Text(
                                text = "Create your public place",
                                color = Color(0xffed4956),
                                fontWeight = FontWeight.Bold,
                                style =  MaterialTheme.typography.h6

                            )
                        }
                    }
                    Divider(thickness = 1.dp, color = Color(0xffED4956))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                        verticalArrangement =Arrangement.SpaceEvenly)

                    {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.2f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "Upload a place image",
                                color = Color(0xffed4956),
                                fontWeight = FontWeight.Bold,
                                style =  MaterialTheme.typography.subtitle1

                            )

                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.8f),verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Center) {
                            Surface(
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(5.dp),
                                border = BorderStroke(width = 0.5.dp, color = Color(0xffed4956)),
                                elevation = 4.dp,
                            )
                            {
                                if(userVM.p_Url.value.isEmpty())Image(
                                    painter = painterResource(id = R.drawable.p_img),
                                    contentDescription = "",
                                    modifier = Modifier.size(150.dp),
                                    contentScale = ContentScale.Crop
                                )else{
                                    AsyncImage(model = userVM.p_Url.value, contentDescription ="" ,
                                        modifier = Modifier.size(150.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f),
                            verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center)
                        {
                            if (userVM.p_Url.value.isEmpty()){
                                OutlinedButton(
                                    onClick = {
                                        launcher.launch("image/*")

                                              },
                                    colors = ButtonDefaults
                                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                                ) {
                                    Text(
                                        text = "Upload",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }else{
                                OutlinedButton(
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Set up information",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        userVM.changeImageState()
                                        navController.navigate(PUB_PLACE_INFO_ROUTE) },
                                    colors = ButtonDefaults
                                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                                ) {
                                    Text(
                                        text = "Next",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
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
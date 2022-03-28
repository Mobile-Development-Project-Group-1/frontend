package com.example.mobile_development_project_group_1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileMOView(nav: NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment =Alignment.CenterHorizontally ) {
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
                    Text(
                        text = "Your Profile",
                        color = Color(0xffed4956),
                        fontWeight = FontWeight.Bold,
                        style =  MaterialTheme.typography.h5

                    )
                }
                Divider(thickness = 1.dp, color = Color(0xffED4956))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState()),

                    )

                {


                }



            }
        }
      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceEvenly) {
          OutlinedButton(
              onClick = {
                  nav.navigate(PROFILE_ROUTE )

              },
              colors = ButtonDefaults
                  .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
          ) {
              Text(
                  text = "Modify",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
              )
          }

          OutlinedButton(
              onClick = {
                  userVM.deleteUser()
                  nav.navigate(HOME_ROUTE)

              },
              colors = ButtonDefaults
                  .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
          ) {
              Text(
                  text = "Delete account",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
              )
          }
      }

    }
}


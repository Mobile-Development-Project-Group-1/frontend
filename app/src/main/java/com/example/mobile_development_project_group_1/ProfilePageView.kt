package com.example.mobile_development_project_group_1



import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



@Composable
fun ProfilePageView(nav:NavHostController) {

    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    var imgUrl by remember {
        mutableStateOf<Uri?>(null)

    }
    val context = LocalContext.current
    var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){

        imgUrl = it
        Toast.makeText(
            context,
            "Profile photos are being updated",
            Toast.LENGTH_SHORT

        ).show()
    }


    imgUrl?.let {
        userVM.setProfileImage(imgUrl!!)

    }


    userVM.getUserData()

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
                ) {
                   Row(modifier = Modifier
                       .fillMaxSize()
                       .padding(5.dp),
                       horizontalArrangement = Arrangement.Center
                       ) {
                       Surface(
                           modifier = Modifier.size(90.dp), shape = CircleShape,
                           border = BorderStroke(width = 0.5.dp, Color(0xffED4956)),
                           elevation = 4.dp,
                       ) {
                           AsyncImage(model = userVM.userdata.value["pictureUrl"].toString(),
                               contentDescription ="image",
                               modifier = Modifier.size(90.dp),
                               contentScale = ContentScale.Crop
                           )
                       
                       }
                       Icon(painter = painterResource(id = R.drawable.ic_create), contentDescription ="Create Image" ,
                            modifier = Modifier
                                .padding(0.dp, 70.dp, 0.dp, 0.dp,)
                                .clickable {
                                    launcher.launch("image/*")

                                }

                           )

                   }
                }
                Divider(thickness = 1.dp, color = Color(0xffED4956))
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .verticalScroll(rememberScrollState()),

                    )

                {
                    MakeTextInfo("First name", userVM.userdata.value["firstName"].toString())
                    MakeTextInfo("Last name", userVM.userdata.value["lastName"].toString())
                    MakeTextInfo("Email", Firebase.auth.currentUser?.email.toString())
                    MakeTextInfo("Phone number", userVM.userdata.value["phoneNumber"].toString())
                    MakeTextInfo("Address", userVM.userdata.value["address"].toString())

                }



            }
        }
        OutlinedButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Modify your profile",
                    Toast.LENGTH_SHORT
                ).show()

                nav.navigate(PROFILE_MODIFY)

            },
            colors = ButtonDefaults
                .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
        ) {
            Text(
                text = "Edit your profile",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}

@Composable
fun MakeTextInfo(title:String , data:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column()
        {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xffED4956)
            )
            Text(text = data, fontSize = 12.sp)
        }

    }
    Divider(thickness = 1.dp)
}
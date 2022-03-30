
// package com.example.mobile_development_project_group_1
// import androidx.compose.foundation.background
// import androidx.compose.foundation.clickable
// import androidx.compose.foundation.layout.*
// import androidx.compose.foundation.shape.RoundedCornerShape
// import androidx.compose.material.Card
// import androidx.compose.material.Icon
// import androidx.compose.material.Text
// import androidx.compose.runtime.Composable
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.painterResource
// import androidx.compose.ui.unit.dp
// import androidx.navigation.NavHostController

// @Composable
// fun ChatView(navController: NavHostController) {
//     Column(
//         modifier = Modifier
//             .fillMaxWidth()
//             .fillMaxHeight(0.95f)
//             .padding(10.dp)
//     ) {
//         Column(
//             modifier = Modifier
//                 .fillMaxWidth()
//                 .padding(10.dp),
//         ) {
//             Card(
//                 modifier = Modifier
//                     .size(42.dp)
//                     .clickable {
//                         navController.navigate(HOME_ROUTE)
//                     },
//                 shape = RoundedCornerShape(30.dp)
//             ) {
//                 Row(
//                     modifier = Modifier.background(Color(0xffed4956)),
//                     verticalAlignment = Alignment.CenterVertically,
//                     horizontalArrangement = Arrangement.Center
//                 ) {
//                     Icon(
//                         painter = painterResource(R.drawable.ic_arrow_left),
//                         contentDescription = "",
//                         tint = Color.White
//                     )
//                 }
//             }
//         } // Back to HomePage button
//         Column() {
//             Text(text = "CHAT PAGE")
//         }
//     }
package com.example.mobile_development_project_group_1.ui.theme


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mobile_development_project_group_1.Message
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class MessageViewModel :ViewModel() {
    var msgs= mutableStateOf(listOf<String>())
    init{
        Firebase.firestore
            .collection("messages")
            .orderBy("created_at")
            .addSnapshotListener{value,error ->
                if(error != null)
                {

                }
                else if (value != null && !value.isEmpty)
                {
                    val msg = mutableListOf<String>()
                    for (d in value.documents)
                    {
                        msg.add(d.get("username").toString()+ ": " + d.get("msg").toString())
                    }
                    msgs.value = msg
                }
            }
    }

    fun addMessange (username: String, msg: String){
        val firestore = Firebase.firestore

        val name= username.split("@")

        val msgs= Message(msg,name[0], Calendar.getInstance().time)
        //print(Calendar.getInstance().time)
        firestore.collection("messages").add(msgs)

    }
}
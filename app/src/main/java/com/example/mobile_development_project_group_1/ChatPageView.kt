package com.example.mobile_development_project_group_1

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobile_development_project_group_1.ui.theme.MessageViewModel

@Composable
fun ConversationViewWindow() {

    val scrollState = rememberScrollState()

    val msgVM: MessageViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .background(Color.White)
            .verticalScroll(state = scrollState),
    ) {

        msgVM.msgs.value.forEach{
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 15.dp),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, Color(0xFFCACACA))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = it
                    )
                }
            }
        }

    }

}

@Composable
fun ConversationView(userVM:UserViewModel, navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .size(36.dp)
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
        ConversationViewWindow()
        Divider(thickness = 2.dp, color = Color(0xffed4956))
        MessageAdding(userVM)
    }
}


@Composable
fun MessageAdding(userVM:UserViewModel) {
    var msgs by remember { mutableStateOf("") }
    val msgVM: MessageViewModel = viewModel()



    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .border(2.dp, Color(0xffed4956))
            ) {
                TextField(
                    value = msgs,
                    onValueChange = { msgs = it },
                    colors = TextFieldDefaults
                        .textFieldColors(
                            backgroundColor = Color.White,
                            textColor = Color(0xffed4956),
                            placeholderColor = Color(0xffed4956)
                        ),
                    singleLine = true,
                    modifier = Modifier.height(50.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {
                        if(msgs.isNotEmpty()){
                            msgVM.addMessange(userVM.username.value,msgs)
                            msgs=""
                        }

                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "Send",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
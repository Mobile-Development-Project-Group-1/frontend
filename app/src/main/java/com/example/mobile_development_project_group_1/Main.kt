package com.example.mobile_development_project_group_1

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

const val HOME_ROUTE = "home"
const val LOGINSIGNUP_ROUTE = "logInSignUp"
const val PROFILE_ROUTE = "profile"

const val ADMIN_ROUTE = "ADMIN"
const val MANAGER_ROUTE = "MANAGER"
const val USER_ROUTE = "USER"

@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()
    val scState = rememberScaffoldState( rememberDrawerState(DrawerValue.Closed) )

    Scaffold(
        scaffoldState = scState,
        topBar = { TopBarView(navController, scState) },
        bottomBar = { BottomBarView() },
        content = { MainContentView(navController) },
        drawerContent = { DrawerLayoutView(navController, scState) }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    NavHost(navController = navController, startDestination = HOME_ROUTE ) {
        composable(route = HOME_ROUTE) { HomeView() }
        composable(route = LOGINSIGNUP_ROUTE) { LoginView(userVM, navController) }
        composable(route = PROFILE_ROUTE) { ProfilePageView() }
    }
}

@Composable
fun TopBarView(navController: NavHostController, scState: ScaffoldState) {

    val scope = rememberCoroutineScope()
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource( R.drawable.ic_menu ),
                tint = Color(0xffed4956),
                contentDescription = "",
                modifier = Modifier.clickable {
                    scope.launch {
                        scState.drawerState.open()
                    }
                }
            )
            if (!userVM.isAnyUser.value) {
                OutlinedButton(
                    onClick = {
                        navController.navigate(LOGINSIGNUP_ROUTE)
                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "LogIn/SignUp",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .background(Color(0xffed4956))
                        .clickable { navController.navigate(HOME_ROUTE) }
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
        Divider( color = Color(0xffed4956), thickness = 2.dp )
    }
}

@Composable
fun BottomBarView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider( color = Color(0xffed4956), thickness = 2.dp )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(10.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box {
                Text(text = "ADVERTISEMENT")
            }
        }
    }
}

@Composable
fun DrawerLayoutView(navController: NavHostController, scState: ScaffoldState) {

    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xffed4956))
                    .clickable { navController.navigate(HOME_ROUTE) }
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = ""
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(20.dp),
        ) {
            if (userVM.isAnyUser.value) {
                OutlinedButton(
                    onClick = {
                        navController.navigate(PROFILE_ROUTE)
                        scope.launch {
                            scState.drawerState.close()
                        }
                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "Profile page",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (userVM.isAnyUser.value) {
                OutlinedButton(
                    onClick = {
                        userVM.logout()
                        scope.launch {
                            scState.drawerState.close()
                        }
                        navController.navigate(HOME_ROUTE)
                    },
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = Color(0xffed4956), contentColor = Color.White)
                ) {
                    Text(
                        text = "Log out",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
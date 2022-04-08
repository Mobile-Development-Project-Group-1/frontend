package com.example.mobile_development_project_group_1

import android.Manifest
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import android.location.LocationProvider as LocationProvider1

const val HOME_ROUTE = "home"
const val LOGIN_SIGNUP_ROUTE = "logInSignUp"
const val PROFILE_ROUTE = "profile"
const val PROFILE_MODIFY ="profile_modify"
const val PUB_PLACE_CREATION_ROUTE = "pub_place_creation"
const val CHAT_ROUTE = "chat"
const val MAP_ROUTE = "map"
const val PUB_PLACE_INFO_ROUTE = "pub_Info"
const val PUB_CREATE_Address_ROUTE= "pub_address"
const val PUB_CREATE_EVENT_ROUTE= "pub_EVENT"

@ExperimentalFoundationApi
@Composable
fun MainScaffoldView() {
    val scaffoldState = rememberScaffoldState()
    val permissionViewModel = PermissionViewModel()
    val navController = rememberNavController()
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    val scState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val pubPlaceVM = viewModel<PubPlaceViewModel>(LocalContext.current as ComponentActivity)
    pubPlaceVM.getPubPlaceInfo()
    userVM.getUserData()

    Scaffold(
        scaffoldState = scState,
        topBar = { TopBarView(navController, scState, scaffoldState, permissionViewModel) },
        bottomBar = { BottomBarView() },
        content = { MainContentView(navController) },
        drawerContent = { DrawerLayoutView(navController, scState) },
        drawerGesturesEnabled = !userVM.isMapOpen.value
    )
}

@ExperimentalFoundationApi
@Composable
fun MainContentView(navController: NavHostController) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    NavHost(navController = navController, startDestination = HOME_ROUTE ) {
        composable (route = HOME_ROUTE) {
            HomeView(navController)
        }

        composable (route = LOGIN_SIGNUP_ROUTE) {
            LoginView(userVM, navController)
        }

        composable (route = PROFILE_ROUTE) {
            ProfilePageView(navController)
        }
        
        composable (route = PROFILE_MODIFY) {
            ProfileMOView(navController)
        }

        composable (route = PUB_PLACE_CREATION_ROUTE) {
            AddNewPubPlaceView(navController)
        }

        composable (route = CHAT_ROUTE) {
            ConversationView(userVM, navController)
        }

        composable (route = MAP_ROUTE) {
            MyMap()
        }

        composable (route = PUB_PLACE_INFO_ROUTE ) {
            CreatePubPlaceInfo(navController)
        }
        
        composable (route = PUB_CREATE_Address_ROUTE ) {
            PubAddresspage(navController)
        }
        
        composable (route = PUB_CREATE_EVENT_ROUTE ) {
            PubEventPage(navController)
        }
    }
}


@Composable
fun TopBarView(
    navController: NavHostController,
    scState: ScaffoldState,
    scaffoldState: ScaffoldState,
    permissionViewModel: PermissionViewModel
) {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ComponentActivity)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val performLocationAction by permissionViewModel.performLocationAction.collectAsState()
    var isActivated by remember { mutableStateOf(false) }

    if (performLocationAction) {
        PermissionUI(
            context = context,
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            permissionRationale = "In order to show map, we require the location permission to be granted.",
            scaffoldState = scaffoldState,
        ) { permissionAction ->
            when (permissionAction) {
                is PermissionAction.OnPermissionGranted -> {

                    permissionViewModel.setPerformLocationAction(false)
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Location Permission Granted!")
                    }
                }
                is PermissionAction.OnPermissionDenied -> {
                    permissionViewModel.setPerformLocationAction(false)
                }
            }
        }
    }

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
            if (userVM.isMapOpen.value) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(36.dp)
                        .clickable {
                            navController.navigate(HOME_ROUTE)
                            userVM.disableDrawer()

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
            } else {
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

            }

            if (!userVM.isMapOpen.value) {
                Icon(
                    painter = painterResource( R.drawable.ic_map ),
                    tint = Color(0xffed4956),
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        if (!isActivated) {
                            permissionViewModel.setPerformLocationAction(true)
                            isActivated = true
                        } else {
                            navController.navigate(MAP_ROUTE)
                            userVM.disableDrawer()
                        }
                    }
                )
            }

            if (!userVM.isAnyUser.value) {
                OutlinedButton(
                    onClick = {
                        navController.navigate(LOGIN_SIGNUP_ROUTE)
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
        DefaultSnackBar(
            snackbarHostState = scaffoldState.snackbarHostState,
            onAction = {
                scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
            }
        )
        Divider( color = Color(0xffed4956), thickness = 2.dp )
    }
}

@Composable
fun BottomBarView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
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

    val context = LocalContext.current
    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth
    var currentUserName by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserName = it.get("firstName").toString()
        }
        
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(20.dp),
        ) {
            if (userVM.isAnyUser.value) {
                Text(
                    text = "Welcome back,",
                    color = Color(0xffed4956),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentUserName,
                    color = Color(0xffed4956),
                    fontSize = 18.sp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(20.dp),
        ) {
            if (userVM.isAnyUser.value) {
                OutlinedButton(
                    onClick = {
                        navController.navigate(PROFILE_ROUTE)
                        scope.launch {
                            scState.drawerState.close()
                        }
                        Toast.makeText(
                            context,
                            " Your profile",
                            Toast.LENGTH_SHORT
                        ).show()

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
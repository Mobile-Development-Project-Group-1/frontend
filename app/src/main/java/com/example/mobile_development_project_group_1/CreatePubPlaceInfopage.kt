package com.example.mobile_development_project_group_1

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreatePubPlaceInfo() {
    val userVM = viewModel<UserViewModel>(LocalContext.current as ViewModelStoreOwner)
    userVM.changeImageState()
}
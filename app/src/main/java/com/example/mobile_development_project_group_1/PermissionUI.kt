package com.example.mobile_development_project_group_1

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.*


@Composable
fun PermissionUI(
    context: Context,
    permission: String,
    permissionRationale: String,
    scaffoldState: ScaffoldState,
    permissionAction: (PermissionAction) -> Unit
) {

    val permissionGranted = Common.checkIfPermissionGranted(context, permission)
    if (permissionGranted) {
        permissionAction(PermissionAction.OnPermissionGranted)
        return

    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionAction(PermissionAction.OnPermissionGranted)
        } else {
            permissionAction(PermissionAction.OnPermissionDenied)
        }
    }

    val showPermissionRationale = Common.shouldShowPermissionRationale(context, permission)

    if (showPermissionRationale) {
        LaunchedEffect(showPermissionRationale) {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = permissionRationale,
                actionLabel = "Grant Access",
                duration = SnackbarDuration.Long
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {
                    permissionAction(PermissionAction.OnPermissionDenied)
                }
                SnackbarResult.ActionPerformed -> {

                    launcher.launch(permission)

                }
            }
        }
    } else {
        SideEffect {
            launcher.launch(permission)
        }
    }
}
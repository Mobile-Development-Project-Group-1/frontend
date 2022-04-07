package com.example.mobile_development_project_group_1

sealed class PermissionAction {
    object OnPermissionGranted: PermissionAction()
    object OnPermissionDenied: PermissionAction()
}
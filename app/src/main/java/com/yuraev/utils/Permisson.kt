package com.yuraev.utils

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requiredPermission(permission: String,): Boolean {

    val state = rememberPermissionState(permission)

    when {
        state.status.isGranted -> {
            Log.d("Permission", "Permission granted")
            return true}
        else -> {
            Log.d("Permission", "Permission Dinaed")
            LaunchedEffect(Unit) {
                state.launchPermissionRequest()
            }

        }
    }
    return false
}
fun getPermissionStorage(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
        Manifest.permission.READ_MEDIA_IMAGES
    }
    else{
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
}

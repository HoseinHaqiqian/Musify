package mx.yts.common.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(permission: String, job: () -> Unit) {
    val permissionState = rememberPermissionState(permission)
    var launchPermissionRequest by rememberSaveable { mutableStateOf(false) }
    when {
        !permissionState.permissionRequested -> {
            Log.d("Permission", "Permission Requested")
            launchPermissionRequest = true
        }
        permissionState.hasPermission -> {
            Log.d("Permission", "Permission Granted 1")
            job.invoke()
        }
        !permissionState.hasPermission -> {
            Log.d("Permission", "Permission Not Granted")
        }
    }
    if (launchPermissionRequest) {
        LaunchedEffect(permissionState) {
            permissionState.launchPermissionRequest()
            launchPermissionRequest = false
        }
    }
}

var permissionGranted = false

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(vararg permission: String, job: () -> Unit) {
    val permissionState = rememberMultiplePermissionsState(permission.toList())
    var launchPermissionRequest by rememberSaveable { mutableStateOf(false) }

    when {
        !permissionState.permissionRequested -> {
            Log.d("Permission", "Permission Requested")
            launchPermissionRequest = true
        }
        permissionState.allPermissionsGranted -> {
            if (!launchPermissionRequest) return
            job.invoke()
            Log.d("Permission", "Permission Granted 2")
            permissionGranted = true
            return
        }
        !permissionState.allPermissionsGranted -> {
            Log.d("Permission", "Permission Not Granted")
        }
    }
    if (launchPermissionRequest) {
        LaunchedEffect(permissionState) {
            permissionState.launchMultiplePermissionRequest()
            launchPermissionRequest = false
        }
    }
}
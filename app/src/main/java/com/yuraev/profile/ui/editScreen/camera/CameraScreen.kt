package com.yuraev.profile.ui.editScreen.camera

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun CameraScreen(
    returnToProfileScreen: () -> Unit,
    viewModel: CameraViewModel = hiltViewModel(),
    ) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {

            viewModel.updateResume(context, "profile_picture.png", it,returnToProfileScreen)
            returnToProfileScreen()
        }
    }
    LaunchedEffect(Unit) {
        launcher.launch(null)
    }


}



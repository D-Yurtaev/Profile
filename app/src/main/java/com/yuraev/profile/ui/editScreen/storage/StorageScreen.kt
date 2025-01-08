package com.yuraev.profile.ui.editScreen.storage

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@Composable
fun StorageScreen(
    returnToEditScreen: () -> Unit ,
    viewModel: StorageViewModel = hiltViewModel()
) {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->

        viewModel.updateResume(uri,returnToEditScreen)

    }
    LaunchedEffect(Unit) {
        launcher.launch(arrayOf("image/*"))
    }

}



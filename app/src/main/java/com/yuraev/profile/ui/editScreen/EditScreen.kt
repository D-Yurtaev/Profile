package com.yuraev.profile.ui.editScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuraev.profile.R
import com.yuraev.profile.ui.ProfileImage
import com.yuraev.profile.ui.model.ResumeUi
import com.yuraev.profile.ui.theme.ProfileTheme
import com.yuraev.utils.getPermissionStorage
import com.yuraev.utils.requiredPermission
import android.Manifest

@Composable
fun EditScreen(
    onBack: () -> Unit = {},
    onDone: () -> Unit = {},
    goToStorage: () -> Unit = {},
    goToCamera: () -> Unit = {},
    viewModel: EditScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val resumeUi by viewModel.resume.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showStorage by remember { mutableStateOf(false) }
    var showCamera by remember { mutableStateOf(false) }
    if (showStorage) {
        val permission = getPermissionStorage()
        val isPermissionGranted = requiredPermission(permission)
        if (isPermissionGranted){goToStorage()}
    }
    if (showCamera) {
        val isPermissionGranted = requiredPermission(Manifest.permission.CAMERA)
        if (isPermissionGranted){ goToCamera() }
    }

    Column(modifier = modifier.fillMaxSize()) {

        if (showDialog) {
            ImageDialog(
                onDismiss = { showDialog = false },
                onGallery = {
                    showStorage = true
                    showDialog = false
                },
                onCamera = {
                    showCamera = true
                    showDialog = false
                }
            )
        }
        ProfileImage(
            resumeUi = resumeUi,
            onClickImage = { showDialog = true },
            appBarRow = {
                AppBarRow(
                    onBack = onBack,
                    onDone = {
                        viewModel.updateResume()
                        onDone()
                    }
                )
            })
        EditInfo(
            resumeUi = resumeUi,
            onChangeName = viewModel::onNameChange,
            onChangeEmail = viewModel::onEmailChange,
            onChangePost = viewModel::onPostChange,
            onChangeLink = viewModel::onLinkChange
        )
    }

}


@Composable
private fun AppBarRow(
    onBack: () -> Unit = {},
    onDone: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {

        Text(
            text = "Редактирование", color = Color.White, fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(32.dp),

            ) {
            Icon(
                Icons.AutoMirrored.Default.ArrowBack, contentDescription = "on Back",
                tint = Color.White,
            )
        }
        IconButton(
            onClick = onDone,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(32.dp),

            ) {
            Icon(
                Icons.Default.Done, contentDescription = "on Done",
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun EditInfo(
    resumeUi: ResumeUi,
    onChangeName: (String) -> Unit = {},
    onChangeEmail: (String) -> Unit = {},
    onChangePost: (String) -> Unit = {},
    onChangeLink: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        EditTextField(
            test = resumeUi.name,
            label = stringResource(R.string.name),
            onValueChange = onChangeName
        )
        EditTextField(
            test = resumeUi.email,
            label = stringResource(R.string.email),
            onValueChange = onChangeEmail
        )
        EditTextField(
            test = resumeUi.post,
            label = stringResource(R.string.post),
            onValueChange = onChangePost
        )
        EditTextField(
            test = resumeUi.urlToPdf?:"",
            label = stringResource(R.string.link),
            onValueChange = onChangeLink
        )

    }
}

@Composable
private fun EditTextField(
    test: String,
    label: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {

    TextField(
        value = test,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}


@Composable
private fun ImageDialog(
    onDismiss: () -> Unit = {},
    onGallery: () -> Unit = {},
    onCamera: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(vertical = 8.dp)
                    .clickable { onGallery() },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.galery),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black)
                    .padding(vertical = 8.dp)
                    .clickable { onCamera() },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    stringResource(R.string.camera),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
        }
    }

}

@Preview
@Composable
private fun EditScreenPreview() {
    ProfileTheme {
        EditScreen()
    }

}



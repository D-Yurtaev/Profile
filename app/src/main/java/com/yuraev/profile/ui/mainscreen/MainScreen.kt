package com.yuraev.profile.ui.mainscreen

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yuraev.profile.R
import com.yuraev.profile.ui.ProfileImage
import com.yuraev.profile.ui.theme.ProfileTheme


@Composable
fun MainScreen(
    onEditClick: () -> Unit = {},
    viewModel: MainScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val resumeUi by viewModel.resume.collectAsState()
    val context = LocalContext.current


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileImage(resumeUi = resumeUi, appBarRow = { AppBarRow(onEditClick = onEditClick) })
        Button(onClick = {
            val url = resumeUi.urlToPdf ?: run {
                Toast.makeText(
                    context,
                    context.getString(R.string.url_not_found),
                    Toast.LENGTH_SHORT
                ).show()
                return@Button
            }
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent) 
            }
            catch (e:Exception){
                Toast.makeText(
                    context,
                    context.getString(R.string.check_lint),
                    Toast.LENGTH_SHORT
                ).show()
                return@Button
            }
            
        }) {
            Text(stringResource(R.string.resume))
        }


    }
}

@Composable
private fun AppBarRow(
    onEditClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {

        Text(
            text = "Профиль", color = Color.White, fontSize = 32.sp,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onEditClick,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(32.dp),

            ) {
            Icon(
                Icons.Default.Edit, contentDescription = "Edit Button",
                tint = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ProfileTheme {
        MainScreen()
    }

}

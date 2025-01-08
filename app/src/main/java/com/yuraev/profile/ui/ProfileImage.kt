package com.yuraev.profile.ui


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.yuraev.profile.R
import com.yuraev.profile.ui.model.ResumeUi
import com.yuraev.profile.ui.theme.ProfileTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileImage(
    resumeUi: ResumeUi = ResumeUi(),
    onClickImage: () -> Unit = {},
    appBarRow: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .background(color = Color(android.graphics.Color.parseColor("#F2F1F6")))
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {

            appBarRow()

            Image(
                painterResource(id = R.drawable.arc_3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
            Log.d("StorageScreen", "Selected URI ToDataCompose: ${resumeUi.uriToImage}")



            GlideImage(
                model = resumeUi.uriToImage ?: R.drawable.ic_launcher_background,
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomCenter)
                    .clickable { onClickImage() },
                contentScale = ContentScale.Crop
            )


        }


        Text(
            text = resumeUi.name,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )


        Text(
            text = resumeUi.email,
            fontWeight = FontWeight.Normal,
            color = Color(android.graphics.Color.parseColor("#747679")),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
        Text(
            text = resumeUi.post,
            fontWeight = FontWeight.Normal,
            color = Color(android.graphics.Color.parseColor("#747679")),
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )

    }
}


@Preview
@Composable
private fun ProfileImagePreview() {
    ProfileTheme {
        ProfileImage()
    }

}

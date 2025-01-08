package com.yuraev.profile.ui.model

import android.net.Uri


data class ResumeUi(
    val name: String = "",
    val email: String = "",
    val post: String = "",
    val urlToPdf: String? = "https://drive.google.com/drive/folders/11-RNnQezerbRIBwZ1g9HyGsqq07rTCKC?usp=sharing",
    val uriToImage: Uri? = null,
)

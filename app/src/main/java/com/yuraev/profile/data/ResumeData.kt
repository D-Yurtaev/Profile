package com.yuraev.profile.data

import kotlinx.serialization.Serializable

@Serializable
data class ResumeData(
    val name: String = "",
    val email: String = "",
    val post: String = "",
    val urlToPdf: String? = "https://drive.google.com/drive/folders/11-RNnQezerbRIBwZ1g9HyGsqq07rTCKC?usp=sharing",
    val uriToImage: String? = null,
)

package com.yuraev.profile.business

import android.net.Uri
import com.yuraev.profile.data.LocalResumeRepository
import com.yuraev.profile.data.ResumeData
import com.yuraev.profile.ui.model.ResumeUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class GetResumeUseCase @Inject constructor(
    private val resumeRepository: LocalResumeRepository
) {
    suspend operator fun invoke(scope: CoroutineScope): ResumeUi {
        return resumeRepository.getResume().toResumeUi()
//        val resumeDataJob = scope.async {  }
//        val resumeData = resumeDataJob.await()
//        return resumeData


    }
}

private fun ResumeData.toResumeUi(): ResumeUi = ResumeUi(
    name = name,
    email = email,
    post = post,
    urlToPdf = urlToPdf,
    uriToImage = uriToImage?.run { Uri.parse(this) }
)

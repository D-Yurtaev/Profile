package com.yuraev.profile.business

import android.util.Log
import com.yuraev.profile.data.LocalResumeRepository
import com.yuraev.profile.data.ResumeData
import com.yuraev.profile.data.ResumeRepositoryInterface
import com.yuraev.profile.ui.model.ResumeUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

class UpdateResumeUseCase @Inject constructor(
    private val resumeRepository: LocalResumeRepository
) {
    suspend operator fun invoke(scope: CoroutineScope, resumeData: ResumeUi) {
        resumeRepository.updateResume(resumeData.toResumeData())
//        val resumeDataJob = scope.async {   }
//        val resumeData = resumeDataJob.await()



    }
}

private fun ResumeUi.toResumeData() = ResumeData(
    name = name,
    email = email,
    post = post,
    urlToPdf = urlToPdf,
    uriToImage = uriToImage?.run { this.toString() }
)

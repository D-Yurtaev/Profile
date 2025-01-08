package com.yuraev.profile.data

import kotlinx.coroutines.flow.Flow

interface ResumeRepositoryInterface {

     suspend fun getResume(): ResumeData
    suspend fun updateResume(resumeData: ResumeData)

}

package com.yuraev.profile.data

import android.content.Context
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class LocalResumeRepository(
    private val context: Context
) : ResumeRepositoryInterface {
    override suspend fun getResume(): ResumeData {

        val resumeData = withContext(Dispatchers.IO) {
            try {
                val documentsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                val file = File(documentsDir, RESUME_FILE)
                if (file.exists()) {
                    val jsonString = file.readText()
                    Json.decodeFromString(jsonString)
                } else {
                    ResumeData()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ResumeData()
            }

        }

        return resumeData
    }

    override suspend fun updateResume(resumeData: ResumeData) {
        withContext(Dispatchers.IO) {
            try {
                val documentsDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                val file = File(documentsDir, RESUME_FILE)
                if (!file.exists()) {
                    file.createNewFile()
                }
                val jsonString = Json.encodeToString(ResumeData.serializer(), resumeData)
                file.writeText(jsonString)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    companion object {
        private const val RESUME_FILE = "resume.json"
    }
}

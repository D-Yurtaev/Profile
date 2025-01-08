package com.yuraev.profile.ui.editScreen.camera

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuraev.profile.business.GetResumeUseCase
import com.yuraev.profile.business.UpdateResumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase,
    private val updateResumeUseCase: UpdateResumeUseCase
):ViewModel(
)  {
    private val uri = MutableStateFlow<Uri?>(null)

    private suspend fun saveImageToExternalStorage(context: Context, fileName: String, bitmap: Bitmap) {
        val uriFrom =  withContext(Dispatchers.IO) {
            try {
                val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName)
                val outputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
                outputStream.close()
                Uri.fromFile(file)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
        uri.value = uriFrom

    }

    fun updateResume(
        context: Context,
        fileName: String,
        bitmap: Bitmap,
        returnToEditScreen: () -> Unit = {}) {

        viewModelScope.launch {
            saveImageToExternalStorage(context, fileName, bitmap)
            val resume = getResumeUseCase(viewModelScope)
            updateResumeUseCase(viewModelScope,resume.copy(uriToImage = uri.value?:Uri.EMPTY))
            returnToEditScreen()
        }

    }
}

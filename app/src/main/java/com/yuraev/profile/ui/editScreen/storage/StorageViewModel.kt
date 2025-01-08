package com.yuraev.profile.ui.editScreen.storage

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuraev.profile.business.GetResumeUseCase
import com.yuraev.profile.business.UpdateResumeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase,
    private val updateResumeUseCase: UpdateResumeUseCase
):ViewModel()  {

    fun updateResume(uri: Uri?,returnToEditScreen: () -> Unit = {}) {
        viewModelScope.launch {
            val resume = getResumeUseCase(viewModelScope)
            updateResumeUseCase(viewModelScope,resume.copy(uriToImage = uri?:Uri.EMPTY))
            returnToEditScreen()
    }

    }
}

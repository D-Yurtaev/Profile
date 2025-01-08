package com.yuraev.profile.ui.editScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuraev.profile.business.GetResumeUseCase
import com.yuraev.profile.business.UpdateResumeUseCase
import com.yuraev.profile.ui.model.ResumeUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditScreenViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase,
    private val updateResumeUseCase: UpdateResumeUseCase
):ViewModel()  {
    private val _resume = MutableStateFlow(ResumeUi())
    val resume = _resume.asStateFlow()

    init {
        getResume()

    }

    private fun getResume() {
        viewModelScope.launch {
            val resume = getResumeUseCase.invoke(viewModelScope)
            _resume.value = resume
            Log.d("StorageScreen", "Selected URI ToDataViewModel: ${_resume.value.uriToImage}")

        }
        }

    fun updateResume() {
        viewModelScope.launch {
            updateResumeUseCase.invoke(viewModelScope,_resume.value)
        }
    }
    fun onNameChange(name: String) {
        _resume.update { it.copy(name = name) }
    }
    fun onEmailChange(email: String) {
        _resume.update { it.copy(email = email) }
    }
    fun onPostChange(post: String) {
        _resume.update { it.copy(post = post) }
    }
    fun onLinkChange(link: String) {
        _resume.update { it.copy(urlToPdf = link) }
    }


}

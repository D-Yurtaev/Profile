package com.yuraev.profile.ui.mainscreen

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuraev.profile.business.GetResumeUseCase
import com.yuraev.profile.data.LocalResumeRepository
import com.yuraev.profile.ui.model.ResumeUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getResumeUseCase: GetResumeUseCase,

) : ViewModel() {
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
}

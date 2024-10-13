package com.seongjae.secureorder.presentation.feature.verification.id

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.VerificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class IDVerificationViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OcrState())
    val state: StateFlow<OcrState> = _state

    fun uploadId(file: File) = viewModelScope.launch {
        runCatching {
            verificationRepository.uploadId(file)
        }.onSuccess {
            _state.value = OcrState(viewState = OcrViewState.Verified(it.extractedText))
        }.onFailure {
            _state.value = OcrState(viewState = OcrViewState.Error(it.message))
        }
    }
}

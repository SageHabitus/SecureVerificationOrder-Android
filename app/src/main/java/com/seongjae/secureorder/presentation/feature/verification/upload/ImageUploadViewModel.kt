package com.seongjae.secureorder.presentation.feature.verification.upload

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
class ImageUploadViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ImageUploadState())
    val state: StateFlow<ImageUploadState> = _state

    fun uploadImage(file: File) = viewModelScope.launch {
        _state.value = ImageUploadState(viewState = ImageUploadViewState.Loading)

        runCatching {
            verificationRepository.uploadImage(file)
        }.onSuccess {
            _state.value = ImageUploadState(viewState = ImageUploadViewState.Success)
        }.onFailure {
            _state.value =
                ImageUploadState(viewState = ImageUploadViewState.Error(it.message))
        }
    }
}
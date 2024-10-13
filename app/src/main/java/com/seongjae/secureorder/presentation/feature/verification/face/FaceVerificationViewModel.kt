package com.seongjae.secureorder.presentation.feature.verification.face

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
class FaceVerificationViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FaceState())
    val state: StateFlow<FaceState> = _state

    fun verifyFaces(face1: File, face2: File) = viewModelScope.launch {
        _state.value = FaceState(viewState = FaceViewState.Loading)

        runCatching {
            verificationRepository.verifyFaces(face1, face2)
        }.onSuccess {
            _state.value = FaceState(viewState = FaceViewState.Verified)
        }.onFailure {
            _state.value = FaceState(viewState = FaceViewState.Error(it.message))
        }
    }
}

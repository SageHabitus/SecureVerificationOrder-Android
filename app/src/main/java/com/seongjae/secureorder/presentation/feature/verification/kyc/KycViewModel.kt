package com.seongjae.secureorder.presentation.feature.verification.kyc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.VerificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.io.File

@HiltViewModel
class KycViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(KycState())
    val state: StateFlow<KycState> = _state

    fun verifyId(file: File) = viewModelScope.launch {
        _state.value = KycState(viewState = KycViewState.Loading)

        runCatching {
            verificationRepository.uploadId(file)
        }.onSuccess {
            _state.value = KycState(viewState = KycViewState.Success)
        }.onFailure { throwable ->
            _state.value = KycState(viewState = KycViewState.Error(throwable.message))
        }
    }
}

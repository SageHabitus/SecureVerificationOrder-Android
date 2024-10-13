package com.seongjae.secureorder.presentation.feature.verification.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.VerificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _otpState = MutableStateFlow(OtpState())
    val otpState: StateFlow<OtpState> = _otpState

    fun verifyOtp(email: String, otp: String) = viewModelScope.launch {
        runCatching {
            verificationRepository.verifyOtp(email = email, otp = otp)
        }.onSuccess {
            _otpState.value = OtpState(viewState = OtpViewState.Success)
        }.onFailure {
            _otpState.value = OtpState(viewState = OtpViewState.Error(it.message))
        }
    }
}
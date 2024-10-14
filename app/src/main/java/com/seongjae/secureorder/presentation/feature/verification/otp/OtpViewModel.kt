package com.seongjae.secureorder.presentation.feature.verification.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.VerificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val verificationRepository: VerificationRepository
) : ViewModel() {

    private val _otpState = MutableStateFlow(OtpState())
    val otpState: StateFlow<OtpState> = _otpState

    fun onOtpChanged(otp: Int) =
        _otpState.update { it.copy(otp = otp) }

    fun onOtpRequested() = viewModelScope.launch {
        verificationRepository.sendOtp()
    }

    fun onOtpSubmitted() = viewModelScope.launch {
        val email = _otpState.value.email
        val otp = _otpState.value.otp

        runCatching {
            verificationRepository.verifyOtp(email = email, otp = otp)
        }.onSuccess {
            _otpState.update { it.copy(viewState = OtpViewState.Success) }
        }.onFailure { e ->
            _otpState.update { it.copy(viewState = OtpViewState.Error(e.message)) }
        }
    }
}
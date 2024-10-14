package com.seongjae.secureorder.presentation.feature.verification.otp

data class OtpState(
    val otp: String = "",
    val email: String = "",
    val viewState: OtpViewState = OtpViewState.Idle,
)

sealed interface OtpViewState {
    data object Idle : OtpViewState
    data object Loading : OtpViewState
    data object Success : OtpViewState
    data class Error(val errorMessage: String?) : OtpViewState
}

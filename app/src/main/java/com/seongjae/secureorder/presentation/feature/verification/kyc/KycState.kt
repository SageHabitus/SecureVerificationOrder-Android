package com.seongjae.secureorder.presentation.feature.verification.kyc

data class KycState(
    val viewState: KycViewState = KycViewState.Idle,
)

sealed interface KycViewState {
    data object Idle : KycViewState
    data object Loading : KycViewState
    data object Success : KycViewState
    data class Error(val errorMessage: String?) : KycViewState
}

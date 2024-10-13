package com.seongjae.secureorder.presentation.feature.verification.id

data class OcrState(
    val viewState: OcrViewState = OcrViewState.Idle,
)

sealed interface OcrViewState {
    data object Idle : OcrViewState
    data object Loading : OcrViewState
    data class Verified(val extractedData: String) : OcrViewState
    data class Error(val errorMessage: String?) : OcrViewState
}
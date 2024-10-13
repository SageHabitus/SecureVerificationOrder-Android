package com.seongjae.secureorder.presentation.feature.verification.face

data class FaceState(
    val viewState: FaceViewState = FaceViewState.Idle,
)

sealed interface FaceViewState {
    data object Idle : FaceViewState
    data object Loading : FaceViewState
    data object Verified : FaceViewState
    data class Error(val errorMessage: String?) : FaceViewState
}

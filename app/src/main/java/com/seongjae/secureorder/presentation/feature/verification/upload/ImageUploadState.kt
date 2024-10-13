package com.seongjae.secureorder.presentation.feature.verification.upload

data class ImageUploadState(
    val viewState: ImageUploadViewState = ImageUploadViewState.Idle,
)

sealed interface ImageUploadViewState {
    data object Idle : ImageUploadViewState
    data object Loading : ImageUploadViewState
    data object Success : ImageUploadViewState
    data class Error(val errorMessage: String?) : ImageUploadViewState
}

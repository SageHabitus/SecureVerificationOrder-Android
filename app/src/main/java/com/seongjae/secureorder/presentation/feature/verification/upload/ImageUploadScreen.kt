package com.seongjae.secureorder.presentation.feature.verification.upload

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen
import java.io.File

@Composable
fun ImageUploadScreen(
    viewModel: ImageUploadViewModel = hiltViewModel(),
    onImageUploaded: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val viewState = state.viewState) {
        is ImageUploadViewState.Idle -> ImageCaptureScreen(onImageCapture = { file ->
            viewModel.uploadImage(file)
        })

        ImageUploadViewState.Loading -> LoadingScreen()

        is ImageUploadViewState.Success -> {
            LaunchedEffect(Unit) {
                onImageUploaded()
            }
        }

        is ImageUploadViewState.Error -> ErrorScreen(
            message = viewState.errorMessage ?: "Upload failed"
        )
    }
}

@Composable
fun ImageCaptureScreen(onImageCapture: (File) -> Unit) {
    // 이미지 고르기
}

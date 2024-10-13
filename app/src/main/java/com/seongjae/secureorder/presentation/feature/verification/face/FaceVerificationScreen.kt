package com.seongjae.secureorder.presentation.feature.verification.face

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen

@Composable
fun FaceVerificationScreen(
    viewModel: FaceVerificationViewModel = hiltViewModel(),
    onVerificationSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val viewState = state.viewState) {
        is FaceViewState.Idle -> {
            // 얼굴 인증을 시작할 수 있는 UI
        }

        is FaceViewState.Loading -> LoadingScreen()
        is FaceViewState.Verified -> {
            onVerificationSuccess()
        }

        is FaceViewState.Error -> ErrorScreen(
            message = viewState.errorMessage ?: "Verification Failed"
        )
    }
}

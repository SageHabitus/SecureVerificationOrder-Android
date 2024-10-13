package com.seongjae.secureorder.presentation.feature.verification.kyc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen
import java.io.File

@Composable
fun KycScreen(
    viewModel: KycViewModel = hiltViewModel(),
    onKycSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val viewState = state.viewState) {
        is KycViewState.Idle -> {
            KycIdleScreen(onVerify = { file ->
                viewModel.verifyId(file)
            })
        }

        is KycViewState.Loading -> {
            LoadingScreen()
        }

        is KycViewState.Success -> {
            LaunchedEffect(Unit) {
                onKycSuccess()
            }
        }

        is KycViewState.Error -> {
            ErrorScreen(message = viewState.errorMessage ?: "KYC 인증 실패")
        }
    }
}

@Composable
fun KycIdleScreen(onVerify: (File) -> Unit) {
    // 신분증 업로드
}


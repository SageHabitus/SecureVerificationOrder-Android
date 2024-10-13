package com.seongjae.secureorder.presentation.feature.verification.otp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen

@Composable
fun OtpScreen(
    viewModel: OtpViewModel = hiltViewModel(),
    onOtpVerified: () -> Unit
) {
    val state by viewModel.otpState.collectAsStateWithLifecycle()

    when (val viewState = state.viewState) {
        is OtpViewState.Idle -> OtpInputScreen(onOtpSubmit = { otp ->
            viewModel.verifyOtp(email = "", otp = otp)
        })

        OtpViewState.Loading -> LoadingScreen()
        is OtpViewState.Success -> {
            LaunchedEffect(Unit) {
                onOtpVerified()
            }
        }

        is OtpViewState.Error -> ErrorScreen(message = viewState.errorMessage ?: "Invalid OTP")
    }
}

@Composable
fun OtpInputScreen(onOtpSubmit: (String) -> Unit) {
    // OTP 입력 UI
    Text("OTP")
}

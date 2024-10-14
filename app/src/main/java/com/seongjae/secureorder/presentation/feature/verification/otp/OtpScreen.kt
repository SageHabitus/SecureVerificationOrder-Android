package com.seongjae.secureorder.presentation.feature.verification.otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
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

    OtpForm(
        state = state,
        onOtpChanged = viewModel::onOtpChanged,
        onOtpRequested = viewModel::onOtpRequested,
        onOtpSubmitted = viewModel::onOtpSubmitted
    )

    when (val viewState = state.viewState) {
        is OtpViewState.Idle -> Unit
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
fun OtpForm(
    state: OtpState,
    onOtpChanged: (Int) -> Unit,
    onOtpRequested: () -> Unit,
    onOtpSubmitted: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = state.otp.toString(),
            modifier = Modifier,
            onValueChange = { onOtpChanged(it.toIntOrNull() ?: 0) },
            label = { Text("OTP 입력") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { onOtpSubmitted() }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onOtpRequested,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("OTP 번호 요청하기")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onOtpSubmitted,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OTP 제출")
        }
    }
}

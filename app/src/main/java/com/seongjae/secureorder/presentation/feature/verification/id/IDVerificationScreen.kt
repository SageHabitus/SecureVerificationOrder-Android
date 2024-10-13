package com.seongjae.secureorder.presentation.feature.verification.id

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen
import java.io.File

@Composable
fun IDVerificationScreen(
    viewModel: IDVerificationViewModel = hiltViewModel(),
    onIdVerified: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val viewState = state.viewState) {
        is OcrViewState.Idle -> IDVerificationIdleScreen { file ->
            viewModel.uploadId(file)
        }

        is OcrViewState.Loading -> LoadingScreen()
        is OcrViewState.Verified -> {
            LaunchedEffect(Unit) {
                onIdVerified() // 인증 성공 시 다음 화면으로 전환
            }
        }

        is OcrViewState.Error -> ErrorScreen(
            message = viewState.errorMessage ?: "Verification Failed"
        )
    }
}

@Composable
fun IDVerificationIdleScreen(onUpload: (File) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            val file = File("/path/to/id") // 파일 경로 (실제 파일 선택 UI 필요)
            onUpload(file)
        }) {
            Text("Upload ID")
        }
    }
}

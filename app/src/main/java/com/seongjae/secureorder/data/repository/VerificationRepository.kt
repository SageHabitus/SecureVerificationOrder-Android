package com.seongjae.secureorder.data.repository

import com.seongjae.secureorder.data.model.OcrDataModel
import com.seongjae.secureorder.data.source.remote.api.VerificationApi
import com.seongjae.secureorder.data.util.toMultipartBody
import java.io.File

/**
 * interface 생략 구현체만 작성
 */
class VerificationRepository(
    private val verificationApi: VerificationApi,
) {

    suspend fun verifyFaces(face1: File, face2: File) =
        verificationApi.compareFaces(
            face1.toMultipartBody(),
            face2.toMultipartBody()
        )

    suspend fun uploadId(file: File): OcrDataModel {
        return verificationApi.verifyIdCard(file.toMultipartBody())
    }

    suspend fun verifyOtp(email: String, otp: String) = verificationApi.verifyOtp(email, otp)

    suspend fun uploadImage(file: File) =
        verificationApi.uploadImage(file.toMultipartBody())
}
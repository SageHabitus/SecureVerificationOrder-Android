package com.seongjae.secureorder.data.source.remote.api

import com.seongjae.secureorder.data.model.OcrDataModel
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface VerificationApi {

    @Multipart
    @POST("/api/face/compare")
    suspend fun compareFaces(
        @Part face1: MultipartBody.Part,
        @Part face2: MultipartBody.Part
    )

    @Multipart
    @POST("/api/kyc/id-verification")
    suspend fun verifyIdCard(
        @Part file: MultipartBody.Part
    ): OcrDataModel

    @POST("/api/auth/send-otp")
    suspend fun sendOtp(
        @Query("email") email: String
    )

    @POST("/api/auth/verify-otp")
    suspend fun verifyOtp(
        @Query("email") email: String,
        @Query("otp") otp: String
    )

    @Multipart
    @POST("/api/images/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    )
}
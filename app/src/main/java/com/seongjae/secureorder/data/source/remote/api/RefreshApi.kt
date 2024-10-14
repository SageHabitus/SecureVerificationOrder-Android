package com.seongjae.secureorder.data.source.remote.api

import com.seongjae.secureorder.data.model.AuthDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshApi {

    @POST("/api/auth/refresh")
    suspend fun refreshToken(
        @Body refreshToken: String
    ): AuthDataModel
}
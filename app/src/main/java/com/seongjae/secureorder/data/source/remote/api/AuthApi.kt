package com.seongjae.secureorder.data.source.remote.api

import com.seongjae.secureorder.data.model.AuthDataModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/api/auth/signin")
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthDataModel
}


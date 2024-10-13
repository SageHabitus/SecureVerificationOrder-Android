package com.seongjae.secureorder.data.repository

import com.seongjae.secureorder.data.model.AuthDataModel
import com.seongjae.secureorder.data.source.api.AuthApi
import com.seongjae.secureorder.data.source.datastore.JwtDataStore
import javax.inject.Inject

/**
 * interface 생략 구현체만 작성
 */
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val jwtDataStore: JwtDataStore
) {

    suspend fun signIn(email: String, password: String): AuthDataModel =
        authApi.signIn(email, password)
            .also { response -> jwtDataStore.saveJwtToken(response.accessToken) }
}
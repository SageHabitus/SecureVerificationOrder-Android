package com.seongjae.secureorder.data.source.remote.auth

import com.seongjae.secureorder.data.source.local.datastore.JwtDataStore
import com.seongjae.secureorder.data.source.remote.api.RefreshApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Authenticator @Inject constructor(
    private val jwtDataStore: JwtDataStore,  // JwtDataStore를 직접 사용
    private val refreshApi: RefreshApi  // 토큰 갱신용 API
) : okhttp3.Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            jwtDataStore.refreshToken.firstOrNull()
        } ?: return null

        val newTokens = runBlocking {
            refreshApi.refreshToken(refreshToken)  // 토큰 갱신 API 호출
        }

        // 새로 발급 받은 토큰을 저장
        newTokens?.let {
            runBlocking {
                jwtDataStore.saveAccessToken(it.accessToken)
                jwtDataStore.saveRefreshToken(it.refreshToken)
            }

            // 새로 발급 받은 Access Token을 포함한 요청으로 재시도
            return response.request.newBuilder()
                .header("Authorization", "Bearer ${it.accessToken}")
                .build()
        }

        return null
    }
}

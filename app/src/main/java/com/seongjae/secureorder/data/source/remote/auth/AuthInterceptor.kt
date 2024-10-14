package com.seongjae.secureorder.data.source.remote.auth

import com.seongjae.secureorder.data.source.local.datastore.JwtDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val jwtDataStore: JwtDataStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.url.encodedPath.contains("/api/auth/signin") ||
            request.url.encodedPath.contains("/api/auth/refresh-token")
        ) {
            return chain.proceed(request)
        }

        val accessToken = runBlocking {
            jwtDataStore.accessToken.firstOrNull()
        }

        val requestWithToken = accessToken?.let {
            request.newBuilder()
                .header("Authorization", "Bearer $it")
                .build()
        } ?: request

        return chain.proceed(requestWithToken)
    }
}

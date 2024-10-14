package com.seongjae.secureorder.data.di

import com.seongjae.secureorder.data.source.remote.api.AuthApi
import com.seongjae.secureorder.data.source.remote.api.MenuApi
import com.seongjae.secureorder.data.source.remote.api.RefreshApi
import com.seongjae.secureorder.data.source.remote.api.VerificationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRefreshApi(@Named("withAuth") retrofit: Retrofit): RefreshApi {
        return retrofit.create(RefreshApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMenuApi(retrofit: Retrofit): MenuApi {
        return retrofit.create(MenuApi::class.java)
    }

    @Singleton
    @Provides
    fun provideVerificationApi(retrofit: Retrofit): VerificationApi {
        return retrofit.create(VerificationApi::class.java)
    }
}
package com.seongjae.secureorder.data.di

import com.seongjae.secureorder.data.repository.AuthRepository
import com.seongjae.secureorder.data.repository.MenuRepository
import com.seongjae.secureorder.data.repository.VerificationRepository
import com.seongjae.secureorder.data.source.remote.api.AuthApi
import com.seongjae.secureorder.data.source.remote.api.MenuApi
import com.seongjae.secureorder.data.source.remote.api.VerificationApi
import com.seongjae.secureorder.data.source.local.dao.MenuDao
import com.seongjae.secureorder.data.source.local.datastore.JwtDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        jwtDataStore: JwtDataStore,
    ): AuthRepository = AuthRepository(authApi = authApi, jwtDataStore = jwtDataStore)

    @Provides
    @Singleton
    fun provideMenuRepository(
        menuDao: MenuDao,
        menuApi: MenuApi
    ): MenuRepository = MenuRepository(menuDao = menuDao, menuApi = menuApi)

    @Provides
    @Singleton
    fun provideVerificationRepository(
        verificationApi: VerificationApi
    ): VerificationRepository = VerificationRepository(verificationApi = verificationApi)
}

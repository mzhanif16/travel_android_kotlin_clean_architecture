package com.mzhnf.data.di

import com.mzhnf.data.repository.AuthRepositoryImpl
import com.mzhnf.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}
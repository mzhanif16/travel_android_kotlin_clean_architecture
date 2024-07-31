package com.mzhnf.data.repository

import com.mzhnf.domain.repository.AuthRepository
import com.mzhnf.network.model.DefaultDTO
import com.mzhnf.network.service.AuthService
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject


class AuthRepositoryImpl  @Inject constructor(private val authService: AuthService): AuthRepository{
    override suspend fun register(requestBody: RequestBody): Response<DefaultDTO> {
        return authService.register(requestBody)
    }
}
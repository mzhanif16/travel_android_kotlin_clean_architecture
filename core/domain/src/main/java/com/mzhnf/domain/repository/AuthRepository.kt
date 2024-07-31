package com.mzhnf.domain.repository

import com.mzhnf.network.model.DefaultDTO
import okhttp3.RequestBody
import retrofit2.Response

interface AuthRepository {
    suspend fun register(requestBody: RequestBody): Response<DefaultDTO>
}
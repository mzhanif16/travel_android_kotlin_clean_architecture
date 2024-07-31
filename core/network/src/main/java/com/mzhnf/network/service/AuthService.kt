package com.mzhnf.network.service

import com.mzhnf.network.model.DefaultDTO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/users/register")
    suspend fun register(
        @Body requestBody: RequestBody
    ): Response<DefaultDTO>
}
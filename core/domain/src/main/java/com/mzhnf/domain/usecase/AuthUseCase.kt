package com.mzhnf.domain.usecase

import com.mzhnf.common.CODE
import com.mzhnf.common.Resource
import com.mzhnf.domain.repository.AuthRepository
import com.mzhnf.model.DefaultModel
import com.mzhnf.network.model.toDefaultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun register(
        username: String,
        email: String,
        password:String
    ): Flow<Resource<DefaultModel>> = flow {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        val requestBody =
            RequestBody.create("application/json".toMediaTypeOrNull(), jsonObject.toString())
        try {
            emit(Resource.Loading())
            val response = authRepository.register(requestBody)
            if (response.isSuccessful) {
                val code = response.code()
                if (CODE.contains(code)) {
                    emit(Resource.Error(response.body()!!.meta.message))
                } else {
                    response.body()?.let {
                        emit(Resource.Success(it.toDefaultModel()))
                    }
                }
            } else {
                emit(
                    Resource.Error(
                        JSONObject(
                            response.errorBody()!!.string()
                        ).getJSONObject("meta").getString("message")
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}
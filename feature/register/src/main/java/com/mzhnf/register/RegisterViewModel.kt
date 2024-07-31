package com.mzhnf.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhnf.common.Resource
import com.mzhnf.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _regisResponse = MutableLiveData<RegisterState>()
    val regisResponse: LiveData<RegisterState> = _regisResponse

    fun register(email: String, username: String, password: String) {
        authUseCase.register(username, email, password).map { result ->
            when (result) {
                is Resource.Success -> {
                    _regisResponse.postValue(
                        RegisterState(
                            success = result.data,
                            isLoading = false
                        )
                    )
                    Log.d("XXX", "1")
                }

                is Resource.Error -> {
                    _regisResponse.value = RegisterState(
                        isLoading = false,
                        error = result.message ?: "Something went wrong"
                    )
                    Log.d("XXX", "2" + result.message)
                }

                is Resource.Loading -> {
                    _regisResponse.value = RegisterState(isLoading = true)
                    Log.d("XXX", "3")
                }

                else -> {

                    Log.d("XXX", "4")
                }
            }
        }.launchIn(viewModelScope)
    }
}
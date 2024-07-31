package com.mzhnf.register

import com.mzhnf.model.DefaultModel

data class RegisterState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: DefaultModel? = null
)

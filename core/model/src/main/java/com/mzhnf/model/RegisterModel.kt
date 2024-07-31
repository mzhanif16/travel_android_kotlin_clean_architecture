package com.mzhnf.model

data class DefaultModel(
    val data: Any? = null,
    val meta: Meta
)
data class Meta(
    val code: Int, // 201
    val message: String, // Success sent otp to your email!
    val status: String // success
)

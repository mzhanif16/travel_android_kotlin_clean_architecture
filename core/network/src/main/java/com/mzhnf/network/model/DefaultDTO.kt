package com.mzhnf.network.model
import com.google.gson.annotations.SerializedName
import com.mzhnf.model.DefaultModel
import com.mzhnf.model.Meta


data class DefaultDTO(
    @SerializedName("data")
    val `data`: Any? = null, // null
    @SerializedName("meta")
    val meta: Meta
)

fun DefaultDTO.toDefaultModel(): DefaultModel {
    return DefaultModel(
        data = data,
        meta =meta
    )
}
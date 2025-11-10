package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordRequestDto(

    @SerialName("password")
    val password: String? = null,

    @SerialName("rePassword")
    val rePassword: String? = null,

    @SerialName("currentPassword")
    val currentPassword: String? = null
)

package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequestDto(

    @SerialName("newPassword")
    val newPassword: String? = null,

    @SerialName("email")
    val email: String? = null
)

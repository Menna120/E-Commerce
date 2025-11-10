package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordResponseDto(

    @SerialName("token")
    val token: String? = null
)

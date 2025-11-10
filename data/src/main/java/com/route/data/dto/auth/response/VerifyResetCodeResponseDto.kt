package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyResetCodeResponseDto(

    @SerialName("status")
    val status: String? = null
)

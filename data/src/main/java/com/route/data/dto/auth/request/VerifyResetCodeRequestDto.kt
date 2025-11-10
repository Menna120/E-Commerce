package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyResetCodeRequestDto(

    @SerialName("resetCode")
    val resetCode: String? = null
)

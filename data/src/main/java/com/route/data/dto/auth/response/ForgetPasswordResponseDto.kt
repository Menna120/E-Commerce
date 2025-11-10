package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordResponseDto(

    @SerialName("statusMsg")
    val statusMsg: String? = null,

    @SerialName("message")
    val message: String? = null
)

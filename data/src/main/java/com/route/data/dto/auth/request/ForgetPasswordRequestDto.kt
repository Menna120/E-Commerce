package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgetPasswordRequestDto(

    @SerialName("email")
    val email: String? = null
)

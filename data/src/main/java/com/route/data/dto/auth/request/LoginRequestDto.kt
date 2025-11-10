package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(

    @SerialName("password")
    val password: String? = null,

    @SerialName("email")
    val email: String? = null
)

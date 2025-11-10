package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(

    @SerialName("message")
    val message: String? = null,

    @SerialName("user")
    val user: UserDto? = null,

    @SerialName("token")
    val token: String? = null
)

@Serializable
data class UserDto(

    @SerialName("role")
    val role: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("phone")
    val phone: String? = null
)

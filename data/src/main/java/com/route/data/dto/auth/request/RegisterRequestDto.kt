package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(

    @SerialName("password")
    val password: String? = null,

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("rePassword")
    val rePassword: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null
)

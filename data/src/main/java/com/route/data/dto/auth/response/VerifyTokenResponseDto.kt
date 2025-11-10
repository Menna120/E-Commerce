package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyTokenResponseDto(

    @SerialName("decoded")
    val decoded: Decoded? = null,

    @SerialName("message")
    val message: String? = null
)

@Serializable
data class Decoded(

    @SerialName("role")
    val role: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("exp")
    val exp: Int? = null,

    @SerialName("iat")
    val iat: Int? = null
)

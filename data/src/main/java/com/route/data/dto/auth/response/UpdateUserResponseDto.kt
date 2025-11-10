package com.route.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserResponseDto(

    @SerialName("message")
    val message: String? = null,

    @SerialName("user")
    val user: UserDto? = null
)

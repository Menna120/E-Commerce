package com.route.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequestDto(

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("email")
    val email: String? = null
)

package com.route.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(

    @SerialName("statusMsg")
    val statusMsg: String? = null,

    @SerialName("message")
    val message: String? = null
)

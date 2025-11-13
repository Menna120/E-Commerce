package com.route.data.dto.address

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressResponseDto(

    @SerialName("data")
    val data: List<AddressDto>? = null,

    @SerialName("message")
    val message: String? = null,

    @SerialName("results")
    val results: Int? = null,

    @SerialName("status")
    val status: String? = null
)

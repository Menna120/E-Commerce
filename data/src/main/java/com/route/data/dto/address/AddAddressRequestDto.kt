package com.route.data.dto.address

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddAddressRequestDto(

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("city")
    val city: String? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("details")
    val details: String? = null
)

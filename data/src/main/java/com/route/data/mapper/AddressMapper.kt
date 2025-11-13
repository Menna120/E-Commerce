package com.route.data.mapper

import com.route.data.data_source.local.database.entity.AddressEntity
import com.route.data.dto.address.AddressDto
import com.route.domain.entity.Address

fun Address.toAddressEntity(): AddressEntity = AddressEntity(
    phone = phone,
    city = city, name = name,
    details = details,
    id = id ?: throw Exception("Address ID not found, cannot convert to AddressEntity")
)

fun AddressEntity.toAddress(): Address = Address(
    phone = phone,
    city = city, name = name,
    details = details,
    id = id
)

fun AddressDto.toAddress(): Address = Address(
    phone = phone,
    city = city, name = name,
    details = details,
    id = id ?: throw Exception("Address ID not found, cannot convert to Address")
)

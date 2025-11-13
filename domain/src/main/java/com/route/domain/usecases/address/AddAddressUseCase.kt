package com.route.domain.usecases.address

import com.route.domain.repo.address.AddressRepository
import javax.inject.Inject

class AddAddressUseCase @Inject constructor(private val addressRepository: AddressRepository) {
    suspend operator fun invoke(
        name: String,
        details: String,
        phone: String,
        city: String
    ) = addressRepository.addAddress(
        name = name,
        details = details,
        phone = phone,
        city = city
    )
}

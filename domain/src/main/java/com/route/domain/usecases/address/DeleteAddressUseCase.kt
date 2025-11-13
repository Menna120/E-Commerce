package com.route.domain.usecases.address

import com.route.domain.repo.address.AddressRepository
import javax.inject.Inject

class DeleteAddressUseCase @Inject constructor(private val addressRepository: AddressRepository) {
    suspend operator fun invoke(addressId: String) =
        addressRepository.deleteAddress(addressId = addressId)
}

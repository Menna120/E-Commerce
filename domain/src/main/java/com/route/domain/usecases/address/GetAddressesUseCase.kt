package com.route.domain.usecases.address

import com.route.domain.repo.address.AddressRepository
import javax.inject.Inject

class GetAddressesUseCase @Inject constructor(private val addressRepository: AddressRepository) {
    suspend operator fun invoke() = addressRepository.getAddresses()
}

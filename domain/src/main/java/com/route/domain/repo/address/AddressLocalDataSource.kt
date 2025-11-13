package com.route.domain.repo.address

import com.route.domain.entity.Address
import kotlinx.coroutines.flow.Flow

interface AddressLocalDataSource {
    fun getAddresses(): Flow<List<Address>>
    suspend fun saveAddresses(addresses: List<Address>)
    suspend fun deleteAddress(id: String)

}

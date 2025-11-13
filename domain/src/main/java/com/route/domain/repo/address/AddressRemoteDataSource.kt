package com.route.domain.repo.address

import com.route.domain.base.Resource
import com.route.domain.entity.Address
import kotlinx.coroutines.flow.Flow

interface AddressRemoteDataSource {
    suspend fun addAddress(
        name: String,
        details: String,
        phone: String,
        city: String
    ): Flow<Resource<List<Address>?>>

    suspend fun deleteAddress(addressId: String): Flow<Resource<List<Address>?>>
    suspend fun getAddresses(): Flow<Resource<List<Address>?>>
}

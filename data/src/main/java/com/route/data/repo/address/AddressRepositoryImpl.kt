package com.route.data.repo.address

import com.route.data.repo.di.IoDispatcher
import com.route.data.util.NetworkManager
import com.route.domain.base.Resource
import com.route.domain.entity.Address
import com.route.domain.repo.address.AddressLocalDataSource
import com.route.domain.repo.address.AddressRemoteDataSource
import com.route.domain.repo.address.AddressRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val networkManager: NetworkManager,
    private val addressLocalDataSource: AddressLocalDataSource,
    private val addressRemoteDataSource: AddressRemoteDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AddressRepository {
    override suspend fun addAddress(
        name: String,
        details: String,
        phone: String,
        city: String
    ): Flow<Resource<List<Address>?>> = withContext(ioDispatcher) {
        addressRemoteDataSource.addAddress(
            name = name,
            details = details,
            phone = phone,
            city = city
        ).onEach { response ->
            if (response is Resource.Success) {
                response.data?.let { addresses ->
                    addressLocalDataSource.saveAddresses(addresses)
                }
            }
        }
    }

    override suspend fun deleteAddress(addressId: String): Flow<Resource<List<Address>?>> =
        withContext(ioDispatcher) {
            addressRemoteDataSource.deleteAddress(addressId = addressId).onEach { response ->
                if (response is Resource.Success) {
                    response.data?.let {
                        addressLocalDataSource.deleteAddress(addressId)
                    }
                }
            }
        }

    override suspend fun getAddresses(): Flow<Resource<List<Address>?>> =
        withContext(ioDispatcher) {
            if (networkManager.isConnected()) {
                addressRemoteDataSource.getAddresses().onEach {
                    if (it is Resource.Success) it.data?.let { addresses ->
                        addressLocalDataSource.saveAddresses(addresses)
                    }
                }
            } else addressLocalDataSource.getAddresses().map { Resource.Success(it) }
        }
}

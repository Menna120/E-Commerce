package com.route.data.data_source.local.database

import com.route.data.data_source.local.database.dao.AddressDao
import com.route.data.mapper.toAddress
import com.route.data.mapper.toAddressEntity
import com.route.domain.entity.Address
import com.route.domain.repo.address.AddressLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressLocalDataSourceImpl @Inject constructor(
    private val addressDao: AddressDao
) : AddressLocalDataSource {

    override fun getAddresses(): Flow<List<Address>> =
        addressDao.getAddresses().map { addresses ->
            addresses.map { it.toAddress() }
        }

    override suspend fun saveAddresses(addresses: List<Address>) =
        addressDao.insertAddresses(addresses.map { it.toAddressEntity() })

    override suspend fun deleteAddress(id: String) = addressDao.deleteAddress(id)
}

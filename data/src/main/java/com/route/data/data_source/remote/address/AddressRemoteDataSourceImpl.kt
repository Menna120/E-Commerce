package com.route.data.data_source.remote.address

import com.route.data.base.BaseRemoteDataSource
import com.route.data.data_source.remote.address.api.AddressService
import com.route.data.dto.address.AddAddressRequestDto
import com.route.data.mapper.toAddress
import com.route.domain.base.Resource
import com.route.domain.base.mapResource
import com.route.domain.entity.Address
import com.route.domain.repo.address.AddressRemoteDataSource
import com.route.domain.repo.local_storage.TokenPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRemoteDataSourceImpl @Inject constructor(
    private val addressService: AddressService,
    private val tokenPrefs: TokenPrefs,
) : BaseRemoteDataSource(), AddressRemoteDataSource {
    override suspend fun addAddress(
        name: String,
        details: String,
        phone: String,
        city: String
    ): Flow<Resource<List<Address>?>> =
        safeAPICall {
            val token = tokenPrefs.getToken() ?: throw kotlin.Exception("Token not found")
            addressService.addAddress(
                token = token,
                requestDto = AddAddressRequestDto(
                    name = name,
                    details = details,
                    phone = phone,
                    city = city
                )
            )
        }.map { it -> it.mapResource { responseDto -> responseDto?.data?.map { addressDto -> addressDto.toAddress() } } }

    override suspend fun deleteAddress(addressId: String): Flow<Resource<List<Address>?>> =
        safeAPICall {
            val token = tokenPrefs.getToken() ?: throw kotlin.Exception("Token not found")
            addressService.deleteAddress(
                addressId = addressId,
                token = token
            )
        }.map { it -> it.mapResource { responseDto -> responseDto?.data?.map { addressDto -> addressDto.toAddress() } } }


    override suspend fun getAddresses(): Flow<Resource<List<Address>?>> =
        safeAPICall {
            val token = tokenPrefs.getToken() ?: throw kotlin.Exception("Token not found")
            addressService.getAddresses(token = token)
        }.map { it -> it.mapResource { responseDto -> responseDto?.data?.map { addressDto -> addressDto.toAddress() } } }
}

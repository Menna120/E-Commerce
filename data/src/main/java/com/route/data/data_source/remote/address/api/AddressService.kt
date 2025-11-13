package com.route.data.data_source.remote.address.api

import com.route.data.dto.address.AddAddressRequestDto
import com.route.data.dto.address.AddressResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressService {
    @POST("addresses")
    suspend fun addAddress(
        @Header("token") token: String,
        @Body requestDto: AddAddressRequestDto
    ): Response<AddressResponseDto?>

    @DELETE("addresses/{addressId}")
    suspend fun deleteAddress(
        @Header("token") token: String,
        @Path("addressId") addressId: String
    ): Response<AddressResponseDto?>

    @GET("addresses")
    suspend fun getAddresses(@Header("token") token: String): Response<AddressResponseDto?>
}

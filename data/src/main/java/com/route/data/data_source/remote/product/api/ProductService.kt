package com.route.data.data_source.remote.product.api

import com.route.data.dto.product.ProductRequestModel
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProducts(): Response<ProductRequestModel?>
}
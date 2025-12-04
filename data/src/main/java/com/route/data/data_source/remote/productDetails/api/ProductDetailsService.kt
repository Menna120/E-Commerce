package com.route.data.data_source.remote.productDetails.api

import com.route.data.dto.productDetails.ProductDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailsService {
    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") productId: String): Response<ProductDetailsModel?>
}
package com.route.domain.repo.productDetails

import com.route.domain.base.Resource
import com.route.domain.entity.productDetails.ProductDetailsEntity
import kotlinx.coroutines.flow.Flow

interface ProductDetailsRemoteDataSource {
    fun getProductDetails(productId:String): Flow<Resource<ProductDetailsEntity?>>
}
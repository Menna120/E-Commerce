package com.route.domain.repo.product

import com.route.domain.base.Resource
import com.route.domain.entity.product.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRemoteDataSource {
    fun getProducts(): Flow<Resource<List<ProductEntity>>>
}
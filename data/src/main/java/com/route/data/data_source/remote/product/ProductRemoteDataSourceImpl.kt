package com.route.data.data_source.remote.product

import com.route.data.base.BaseRemoteDataSource
import com.route.data.data_source.remote.product.api.ProductService
import com.route.data.mapper.product.toEntity
import com.route.domain.base.Resource
import com.route.domain.base.mapResource
import com.route.domain.entity.product.ProductEntity
import com.route.domain.repo.product.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRemoteDataSourceImpl @Inject constructor(
    private val service: ProductService
): BaseRemoteDataSource(), ProductRemoteDataSource {
    override fun getProducts(): Flow<Resource<List<ProductEntity>>> =
        safeAPICall {
            service.getProducts()
        }.map { resource ->
            resource.mapResource { response ->
                response?.data?.map { it!!.toEntity() } ?: emptyList()
            }
        }
}
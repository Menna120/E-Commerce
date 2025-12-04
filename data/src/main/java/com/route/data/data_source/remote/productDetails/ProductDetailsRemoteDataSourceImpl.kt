package com.route.data.data_source.remote.productDetails

import com.route.data.base.BaseRemoteDataSource
import com.route.data.data_source.remote.productDetails.api.ProductDetailsService
import com.route.data.mapper.productDetails.toEntity
import com.route.domain.base.Resource
import com.route.domain.base.mapResource
import com.route.domain.entity.productDetails.ProductDetailsEntity
import com.route.domain.repo.productDetails.ProductDetailsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: ProductDetailsService
) : BaseRemoteDataSource(), ProductDetailsRemoteDataSource {
    override fun getProductDetails(productId: String): Flow<Resource<ProductDetailsEntity?>> =
        safeAPICall {
            service.getProductDetails(productId)
        }.map { resource ->
            resource.mapResource { response ->
                response?.toEntity()
            }
        }
}

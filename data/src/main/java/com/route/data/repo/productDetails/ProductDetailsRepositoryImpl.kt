package com.route.data.repo.productDetails

import com.route.data.data_source.local.database.db.ECommerceDatabase
import com.route.data.localStorage.entity.ProductDetailsCacheEntity
import com.route.domain.base.Resource
import com.route.domain.entity.productDetails.DataEntity
import com.route.domain.entity.productDetails.ProductDetailsEntity
import com.route.domain.repo.productDetails.ProductDetailsRemoteDataSource
import com.route.domain.repo.productDetails.ProductDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductDetailsRemoteDataSource,
    private val database: ECommerceDatabase
) : ProductDetailsRepository {
    override fun getProductDetails(productId: String): Flow<Resource<ProductDetailsEntity?>> = flow {
        emit(Resource.Loading())
        val dao = database.productDetailsDao()
        try {
            remoteDataSource.getProductDetails(productId).collect { resource ->
                if (resource is Resource.Success) {
                    val data = resource.data?.data
                    if (data != null) {
                        val cacheEntity = ProductDetailsCacheEntity(
                            id = data.id ?: productId,
                            title = data.title,
                            description = data.description,
                            price = data.price?.toDouble(),
                            imageCover = data.imageCover,
                            images = data.images,
                            rating = (data.ratingsAverage as? Number)?.toDouble(),
                            sold = data.sold
                        )
                        dao.insertProductDetails(cacheEntity)
                    }
                }
            }
        } catch (e: Exception) {
        }
        emitAll(
            dao.getProductDetails(productId).map { cachedItem ->
                if (cachedItem == null) {
                    Resource.Error("No cached data found")
                } else {
                    Resource.Success(
                        ProductDetailsEntity(
                            data = DataEntity(
                                id = cachedItem.id,
                                title = cachedItem.title,
                                description = cachedItem.description,
                                price = cachedItem.price?.toInt(),
                                imageCover = cachedItem.imageCover,
                                images = cachedItem.images,
                                ratingsAverage = cachedItem.rating,
                                sold = cachedItem.sold
                            )
                        )
                    )
                }
            }
        )
    }
}
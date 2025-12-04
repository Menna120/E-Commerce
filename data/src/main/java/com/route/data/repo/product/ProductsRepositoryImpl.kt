package com.route.data.repo.product

import com.route.data.localStorage.dao.ProductsDao
import com.route.data.localStorage.product.ProductCacheEntity
import com.route.data.localStorage.product.toProductEntity
import com.route.domain.base.Resource
import com.route.domain.entity.product.ProductEntity
import com.route.domain.repo.product.ProductRemoteDataSource
import com.route.domain.repo.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val productsDao: ProductsDao
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<ProductEntity>>> = flow {

        emit(Resource.Loading())

        try {

            remoteDataSource.getProducts().collect { apiResource ->
                if (apiResource is Resource.Success) {
                    val remoteProducts = apiResource.data ?: emptyList()
                    val cacheEntities = remoteProducts.map {
                        ProductCacheEntity(
                            id = it.id ?: "",
                            title = it.title,
                            description = it.description,
                            price = it.price,
                            imageCover = it.imageCover,
                            rating = it.rating
                        )
                    }
                    productsDao.clearAndInsert(cacheEntities)
                }
            }
        } catch (e: Exception) {
        }
        emitAll(
            productsDao.getProducts().map { localList ->
                if (localList.isEmpty()) {
                    Resource.Error("No Internet & No Cached Data")
                } else {
                    Resource.Success(localList.map { it.toProductEntity() })
                }
            }
        )
    }
}
package com.route.data.localStorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.route.data.localStorage.product.ProductCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductCacheEntity>)

    @Query("DELETE FROM products_cache")
    suspend fun clearProducts()

    @Transaction
    suspend fun clearAndInsert(products: List<ProductCacheEntity>) {
        clearProducts()
        insertProducts(products)
    }

    @Query("SELECT * FROM products_cache")
    fun getProducts(): Flow<List<ProductCacheEntity>>
}
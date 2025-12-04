package com.route.data.localStorage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.data.localStorage.entity.ProductDetailsCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductDetails(product: ProductDetailsCacheEntity)

    @Query("SELECT * FROM product_details_cache WHERE id = :productId")
    fun getProductDetails(productId: String): Flow<ProductDetailsCacheEntity?>
}
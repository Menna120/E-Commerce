package com.route.data.localStorage.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.route.domain.entity.product.ProductEntity

@Entity(tableName = "products_cache")
data class ProductCacheEntity(
    @PrimaryKey
    val id: String,
    val title: String?,
    val description: String?,
    val price: Double?,
    val imageCover: String?,
    val rating: Double?
)

fun ProductCacheEntity.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        imageCover = imageCover,
        rating = rating
    )
}
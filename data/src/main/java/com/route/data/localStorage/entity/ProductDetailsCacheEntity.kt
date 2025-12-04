package com.route.data.localStorage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_details_cache")
data class ProductDetailsCacheEntity(
    @PrimaryKey
    val id: String,
    val title: String?,
    val description: String?,
    val price: Double?,
    val imageCover: String?,
    val images: List<String>?,
    val rating: Double?,
    val sold: Int?
)
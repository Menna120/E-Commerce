package com.route.domain.entity.product

data class ProductEntity(
    val id: String? =null,
    val title: String? =null,
    val description: String? =null,
    val price: Double? =null,
    val currency: String? =null,
    val imageCover: String? =null,
    val images: List<String>? =null,
    val brand: String? =null,
    val categoryIds: List<String>? =null,
    val rating: Double? =null,
    val stock: Int? =null,
    val isActive: Boolean? =false
)
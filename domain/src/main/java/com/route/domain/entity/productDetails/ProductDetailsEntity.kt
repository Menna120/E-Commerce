package com.route.domain.entity.productDetails

data class ProductDetailsEntity(
    val data: DataEntity? = null
)

data class CategoryEntity(
    val image: String? = null,
    val name: String? = null,
    val id: String? = null,
    val slug: String? = null
)

data class BrandEntity(
    val image: String? = null,
    val name: String? = null,
    val id: String? = null,
    val slug: String? = null
)

data class DataEntity(
    val sold: Int? = null,
    val images: List<String>? = null,
    val quantity: Int? = null,
    val imageCover: String? = null,
    val description: String? = null,
    val title: String? = null,
    val ratingsQuantity: Int? = null,
    val ratingsAverage: Double? = null,
    val createdAt: String? = null,
    val reviews: List<Any?>? = null,
    val price: Int? = null,
    val v: Int? = null,
    val id: String? = null,
    //val id: String? = null,
    val subcategory: List<SubcategoryItemEntity?>? = null,
    val category: CategoryEntity? = null,
    val brand: BrandEntity? = null,
    val slug: String? = null,
    val updatedAt: String? = null
)

data class SubcategoryItemEntity(
    val name: String? = null,
    val id: String? = null,
    val category: String? = null,
    val slug: String? = null
)


package com.route.data.mapper.productDetails

import com.route.data.dto.productDetails.Brand
import com.route.data.dto.productDetails.Category
import com.route.data.dto.productDetails.DataModel
import com.route.data.dto.productDetails.ProductDetailsModel
import com.route.data.dto.productDetails.SubcategoryItem
import com.route.domain.entity.productDetails.BrandEntity
import com.route.domain.entity.productDetails.CategoryEntity
import com.route.domain.entity.productDetails.DataEntity
import com.route.domain.entity.productDetails.ProductDetailsEntity
import com.route.domain.entity.productDetails.SubcategoryItemEntity

fun ProductDetailsModel.toEntity(): ProductDetailsEntity =
    ProductDetailsEntity(
        data = data?.toEntity()
    )
fun DataModel.toEntity(): DataEntity =
    DataEntity(
        sold,
        images,
        quantity,
        imageCover,
        description,
        title,
        ratingsQuantity,
        ratingsAverage,
        createdAt,
        reviews,
        price,
        v ,
        id ,
        subcategory?.map { it?.toEntity() },
        category?.toEntity(),
        brand?.toEntity(),
        slug,
        updatedAt
    )
fun SubcategoryItem.toEntity(): SubcategoryItemEntity = SubcategoryItemEntity(name,id,category,slug)
fun Category.toEntity(): CategoryEntity = CategoryEntity(image,name,id,slug)
fun Brand.toEntity(): BrandEntity = BrandEntity(image,name,id,slug)
package com.route.data.mapper.product

import com.route.data.dto.product.DataItem
import com.route.domain.entity.product.ProductEntity

fun DataItem.toEntity(): ProductEntity =
    ProductEntity(
        id = id,
        title = title,
        description = description,
        price = price?.toDouble(),
        currency = "EGP",
        imageCover = imageCover,
        images = images?.filterNotNull(),
        brand = brand?.name,
        categoryIds = listOfNotNull(category?.id),
        rating = ratingsAverage ?: 0.0,
        stock = quantity,
        isActive = true
    )
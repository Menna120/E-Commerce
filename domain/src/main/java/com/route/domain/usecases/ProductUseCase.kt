package com.route.domain.usecases

import com.route.domain.repo.product.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductRepository) {
    fun invoke() = repository.getProducts()
}
package com.route.domain.usecases

import com.route.domain.repo.productDetails.ProductDetailsRepository
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val repository: ProductDetailsRepository
) {
    operator fun invoke(productId: String) =
        repository.getProductDetails(productId)
}

package com.route.e_commerce.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.base.Resource
import com.route.domain.entity.productDetails.ProductDetailsEntity
import com.route.domain.usecases.ProductDetailsUseCase
import com.route.e_commerce.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsUseCase: ProductDetailsUseCase
): ViewModel() {

    val productDetails = MutableStateFlow<Resource<ProductDetailsEntity?>>(Resource.Initial())

    fun getProductDetails(productId: String) {
        viewModelScope.launch {
            getProductDetailsUseCase(productId).collect {
                productDetails.value = it
            }
        }
    }
}

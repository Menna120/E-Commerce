package com.route.e_commerce.screens.main.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.base.Resource
import com.route.domain.entity.product.ProductEntity
import com.route.domain.usecases.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: ProductUseCase
) : ViewModel() {

    private var allProducts: List<ProductEntity> = emptyList()
    private val _products = MutableStateFlow<Resource<List<ProductEntity>>>(Resource.Initial())
    val products = _products.asStateFlow()
    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _products.value = Resource.Loading()
            getProductsUseCase.invoke().collect { resource ->
                if (resource is Resource.Success) {
                    allProducts = resource.data ?: emptyList()
                }
                _products.value = resource
            }
        }
    }
    fun searchProducts(query: String) {
        if (_products.value !is Resource.Success) return

        val filteredList = if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter {
                it.title?.contains(query, ignoreCase = true) == true
            }
        }
        _products.value = Resource.Success(filteredList)
    }
}

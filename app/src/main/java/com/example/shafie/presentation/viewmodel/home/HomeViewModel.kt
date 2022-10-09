package com.example.shafie.presentation.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shafie.domain.model.Product
import com.example.shafie.domain.usecases.AddProductToCartUseCase
import com.example.shafie.domain.usecases.FetchProductsFromApiUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchProductsFromApiUseCase: FetchProductsFromApiUseCase = FetchProductsFromApiUseCase(),
    private val addProductToCartUseCase: AddProductToCartUseCase = AddProductToCartUseCase()
) : ViewModel() {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getOnlineProducts()
    }

    private val coroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            _error.value = throwable.message.orEmpty()
        }


    private val products = MutableLiveData<List<Product>>()
    var productLiveData: LiveData<List<Product>> = products

    fun addProductToCart(id: Int, added: Boolean) {
        viewModelScope.launch {
            val updatedProducts = addProductToCartUseCase(id = id, addedToCart = added)
            products.value = updatedProducts
        }
    }


    private fun getOnlineProducts() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val productData = fetchProductsFromApiUseCase()
            products.value = productData

        }
    }

}
package com.example.shafie.presentation.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shafie.domain.model.Product
import com.example.shafie.domain.usecases.FetchProductsFromApiUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchProductsFromApiUseCase: FetchProductsFromApiUseCase = FetchProductsFromApiUseCase()
) : ViewModel() {

    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    init {
        getOnlineProducts()   //calling this method to hit the api as soon as HomeViewModel class
        //gets initialized.
    }

//    private val coroutineExceptionHandler =
//        CoroutineExceptionHandler { _, throwable ->
//            if (throwable is UiException) {
//                when (throwable) {
//                    UiException.EmptyEmail -> _emailError.value = "Your email is empty"
//                    UiException.EmptyPassword -> ""
//                }
//            }
//        }

    private val products = MutableLiveData<List<Product>>()
    var productLiveData: LiveData<List<Product>> =
        products   // live data variable which gets data back from the mutable live data to observe in home activity.


    private fun getOnlineProducts() {
        viewModelScope.launch() {
            val productData =
                fetchProductsFromApiUseCase() //variable to store the data that comes from api
            products.postValue(productData)
            //getting the data stored in mutable list
        }
    }

}
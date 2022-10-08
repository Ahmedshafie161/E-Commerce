package com.example.shafie.presentation.viewmodel.cart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shafie.data.repo.ProductRepoImpl

class MainViewModelFactoryCart(private val repository: ProductRepoImpl)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(repository, application = Application()) as T
    }
}
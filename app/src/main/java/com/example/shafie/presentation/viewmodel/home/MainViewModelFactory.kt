package com.example.shafie.presentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shafie.data.repo.ProductRepo

class MainViewModelFactory(private val repository: ProductRepo)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, application = Application()) as T
    }
}
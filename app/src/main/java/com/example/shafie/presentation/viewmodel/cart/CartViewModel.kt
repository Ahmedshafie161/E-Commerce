package com.example.shafie.presentation.viewmodel.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shafie.data.dbhelper.DbEntity
import com.example.shafie.data.repo.ProductRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val repository: ProductRepo, application: Application) : AndroidViewModel(application) {

    init {
        getProducts()   //calling this method to get the data stored in database as soon as this class
    }

    private val products = MutableLiveData<List<DbEntity>>()
    var productLiveData: LiveData<List<DbEntity>> = products   // live data variable which gets data back from the mutable live data to observe in CartFragment.

    private fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch{
            val productData= repository.getProductsFromDatabase() //variable to get the data from the database
            products.postValue(productData)   //getting the data stored in mutable list
        }
    }

}
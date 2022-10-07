package com.example.shafie.presentation.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shafie.data.repo.ProductRepo
import com.example.shafie.network.modelapi.OnlineEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepo, application: Application) :AndroidViewModel(application) {

    init {
        getOnlineProducts()   //calling this method to hit the api as soon as HomeViewModel class
                        //gets initialized.
    }

    private val products =MutableLiveData<List<OnlineEntity>>()
    var productLiveData:LiveData<List<OnlineEntity>> = products   // live data variable which gets data back from the mutable live data to observe in home activity.


    private fun getOnlineProducts(){
        CoroutineScope(Dispatchers.IO).launch{
            val productData= repository.getResultFromApi().body()?.products //variable to store the data that comes from api
            products.postValue(productData)   //getting the data stored in mutable list
        }
    }

}
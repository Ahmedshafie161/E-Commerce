package com.example.shafie.data.repo

import com.example.shafie.data.dbhelper.CustomRoomDb
import com.example.shafie.data.dbhelper.DbEntity
import com.example.shafie.network.InterfaceAPI
import com.example.shafie.network.RetrofitHelper
import com.example.shafie.network.modelapi.DataClassAPI
import retrofit2.Response

class ProductRepo(private val database:CustomRoomDb, private val retroFit:RetrofitHelper) {

    suspend fun getResultFromApi():Response<DataClassAPI>{
        val newsApi = retroFit.getInstance().create(InterfaceAPI::class.java)
        return newsApi.getProductsFromApi()
    }

    suspend fun addProductsToDatabase(products:DbEntity){
        database.productDao().addProductToDatabase(products)
    }

    fun getProductsFromDatabase():List<DbEntity>{
        return database.productDao().getProductFromDatabase()
    }



}
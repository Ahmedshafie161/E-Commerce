package com.example.shafie

import android.app.Application
import com.example.shafie.data.dbhelper.CustomRoomDb
import com.example.shafie.data.repo.ProductRepo
import com.example.shafie.network.RetrofitHelper

class CustomApplication: Application() {

    lateinit var productRepository: ProductRepo
    lateinit var productDatabase:CustomRoomDb
    lateinit var retrofit: RetrofitHelper

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        retrofit =RetrofitHelper
        productDatabase = CustomRoomDb.getDataBase(applicationContext)
        productRepository = ProductRepo(productDatabase,retrofit )

    }

}
package com.example.shafie

import android.app.Application
import androidx.room.RoomDatabase
import com.example.shafie.data.local.CustomRoomDb
import com.example.shafie.data.remote.RetrofitHelper
import com.example.shafie.data.repo.ProductRepoImpl

class CustomApplication : Application() {

    lateinit var productRepository: ProductRepoImpl
    companion object{
        lateinit var productDatabase: CustomRoomDb
    }
    lateinit var retrofit: RetrofitHelper


    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        retrofit = RetrofitHelper
        productDatabase = CustomRoomDb.getDataBase(applicationContext)
        productRepository = ProductRepoImpl(productDatabase, retrofit)

    }

}
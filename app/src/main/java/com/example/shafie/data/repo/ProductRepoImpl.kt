package com.example.shafie.data.repo

import com.example.shafie.CustomApplication
import com.example.shafie.data.local.CustomRoomDb
import com.example.shafie.data.remote.InterfaceAPI
import com.example.shafie.data.remote.RetrofitHelper
import com.example.shafie.data.remote.dtos.ProductDto

class ProductRepoImpl(
    private val database: CustomRoomDb = CustomApplication.productDatabase,
    private val retroFit: RetrofitHelper = RetrofitHelper
) :
    ProductRepo {
    override suspend fun getResultFromApi(): List<ProductDto> {
        return retroFit.getInstance().create(InterfaceAPI::class.java).getProductsFromApi().products
    }

    override suspend fun addProductToDatabase(product: ProductDto) {
        database.productDao().addProductToDatabase(product)
    }

    override suspend fun getProducts(): List<ProductDto> {
        return database.productDao().getProductFromDatabase()
    }

}
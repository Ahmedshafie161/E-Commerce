package com.example.shafie.data.repo

import com.example.shafie.CustomApplication
import com.example.shafie.data.local.AppDatabase
import com.example.shafie.data.local.entity.PartialProduct
import com.example.shafie.data.remote.InterfaceAPI
import com.example.shafie.data.remote.RetrofitHelper
import com.example.shafie.data.remote.dtos.ProductDto
import com.example.shafie.data.remote.dtos.ProductObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class ProductRepoImpl(
    private val database: AppDatabase = CustomApplication.productDatabase,
    private val retroFit: RetrofitHelper = RetrofitHelper
) : ProductRepo {
    override suspend fun fetchAllProducts(): List<ProductDto> {
        return withContext(Dispatchers.IO) {
            try {
                refreshProducts()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        if (database.productDao().getAll()
                                .isNotEmpty()
                        ) throw Exception("Something went wrong. We have no data")
                    }

                    else -> throw e
                }
            }

            return@withContext database.productDao().getAll()

        }
    }

    override suspend fun refreshProducts() {
        val remoteProducts = getRemoteProducts().products
        val productsInCart = getAllProductsInCart()
        database.productDao().addAll(remoteProducts)
        database.productDao().updateAll(productsInCart.map {
            PartialProduct(id = it.id ?: 0, addedToCart = true)
        })
    }

    override suspend fun getAllProductsInCart(): List<ProductDto> {
        return database.productDao().getAllProductsAddedToCart()
    }

    override suspend fun getRemoteProducts(): ProductObject {
        return retroFit.getInstance().create(InterfaceAPI::class.java).getProductsFromApi()
    }

    override suspend fun addProductToCart(productId: Int, addedToCart: Boolean): List<ProductDto> {
        return withContext(Dispatchers.IO) {
            database.productDao().update(
                PartialProduct(id = productId, addedToCart = addedToCart)
            )
            return@withContext database.productDao().getAll()
        }


    }

}
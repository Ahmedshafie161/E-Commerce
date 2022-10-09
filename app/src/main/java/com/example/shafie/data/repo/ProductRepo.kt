package com.example.shafie.data.repo

import com.example.shafie.data.remote.dtos.ProductDto
import com.example.shafie.data.remote.dtos.ProductObject

interface ProductRepo {
    suspend fun fetchAllProducts(): List<ProductDto>?

    suspend fun refreshProducts()

    suspend fun getAllProductsInCart(): List<ProductDto>

    suspend fun getRemoteProducts(): ProductObject

    suspend fun addProductToCart(
        productId: Int,
        addedToCart: Boolean
    ): List<ProductDto>
}
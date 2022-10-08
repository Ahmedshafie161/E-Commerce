package com.example.shafie.data.repo

import com.example.shafie.data.remote.dtos.ProductDto

interface ProductRepo {
    suspend fun getResultFromApi(): List<ProductDto>?

    suspend fun addProductToDatabase(product: ProductDto)

    suspend fun getProducts(): List<ProductDto>
}
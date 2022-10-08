package com.example.shafie.domain.model

import com.example.shafie.data.remote.dtos.ProductDto

data class Product(
    val name: String,
    val price: String,
    val imageUrl: String,
    val rating: Int,
)

fun ProductDto.toDomain(): Product {
    return Product(name.orEmpty(), price.orEmpty(), imageUrl.orEmpty(), rating ?: 0)
}

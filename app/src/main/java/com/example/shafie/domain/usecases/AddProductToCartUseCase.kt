package com.example.shafie.domain.usecases

import com.example.shafie.data.repo.ProductRepo
import com.example.shafie.data.repo.ProductRepoImpl
import com.example.shafie.domain.model.toDomain

class AddProductToCartUseCase(
    private val productRepo: ProductRepo = ProductRepoImpl()
) {
    suspend operator fun invoke(
        id: Int,
        addedToCart: Boolean
    ) = productRepo.addProductToCart(productId = id, addedToCart = !addedToCart)
        .map { it.toDomain() }
}